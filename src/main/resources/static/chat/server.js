import http from "http";
import { Server } from "socket.io";
import { instrument } from "@socket.io/admin-ui";
import express from "express";

const mysql = require('mysql2');

const connection = mysql.createConnection({
    host: '158.247.211.246', // your host name
    user: 'wuser', // your database user
    password: 'dbtoU!12', // your database password
    database: 'test_su' // your database name
});

// Connect to the database
connection.connect((err) => {
    if (err) {
        console.error('Error connecting to database:', err);
        return;
    }
    console.log('Connected to database!');
});

// express 초기화
const app = express();

app.use(express.static(__dirname + "/views"));
app.use("/public", express.static(__dirname + "/public"));
app.get("/", (_, res) => res.sendFile("/views/home.html"));
app.get("/*", (_, res) => res.redirect("/chat"));

app.get('/home', function(req, res) {
    res.render('home', { nickname: req.session.nickname });
});

// socket.io 지원을 위해 http 모듈에서 제공하는 메서드로 서버를 초기화
const httpServer = http.createServer(app);

// 웹 소켓 서버를 초기화
const wsServer = new Server(httpServer, {
    cors: {
        origin: ["https://admin.socket.io"],
        credentials: true,
    },
});

instrument(wsServer, {
    auth: false,
});

app.post('/login', function(req, res) {
    // process the login request and validate the user's credentials
    // ...
    // if the user is authenticated, set the session variable
    req.session.nickname = req.body.nickname;
    res.redirect('/home');
});

function publicRooms() {
    const {
        sockets: {
            adapter: { sids, rooms },
        },
    } = wsServer;
    const publicRooms = [];
    rooms.forEach((_, key) => {
        if (sids.get(key) === undefined) {
            publicRooms.push(key);
        }
    });
    return publicRooms;
}

function countRoom(roomName) {
    return wsServer.sockets.adapter.rooms.get(roomName)?.size;
}

wsServer.on("connection", (socket) => {
    // 기본 닉네임 "익명"
    socket["nickname"] = "Anon";
    //이벤트 발생 시 콘솔에 출력
    socket.onAny((event) => {
        console.log(`Socket Event: ${event}`);
    });
    socket.on("enter_room", (roomName, done) => {
        socket.join(roomName);
        done();
        socket.to(roomName).emit("welcome", socket.nickname, countRoom(roomName));
        wsServer.sockets.emit("room_change", publicRooms());
    });
    socket.on("disconnecting", () => {
        socket.rooms.forEach((room) =>
            socket.to(room).emit("bye", socket.nickname, countRoom(room) - 1)
        );
    });
    socket.on("disconnect", () => {
        wsServer.sockets.emit("room_change", publicRooms());
    });
    socket.on("new_message", (msg, room, done) => {
        const query = `INSERT INTO messages (chat_room_id, message_content, m_nick) VALUES (${room}, '${msg}', '${socket.nickname}')`;
        connection.query(query, (error, results, fields) => {
            if (error) throw error;
            console.log('Inserted message into the database.');
        });
        socket.to(room).emit("new_message", `${socket.nickname}: ${msg}`);
        done();
    });

    socket.on("get_nickname", (memberId, done) => {
        const query = `SELECT nickname FROM member WHERE id = ${memberId}`;
        connection.query(query, (error, results, fields) => {
            if (error) throw error;
            const nickname = results[0].nickname;
            done(nickname);
        });
    });

    socket.on("nickname", (nickname) => (socket["nickname"] = nickname));
});

const handleListen = () => console.log(`Listening on http://localhost:8086`);
httpServer.listen(8086, handleListen);
