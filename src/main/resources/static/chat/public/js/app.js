// 소켓 객체 생성 및 서버 연결
const socket = io("http://localhost:3036", { path: "/socket.io", transports: ["websocket"] });

// HTML 문서 내의 요소들을 가져와 변수에 저장
const welcome = document.getElementById("welcome");
/*const form = welcome.querySelector("form");*/
const room = document.getElementById("room");

// 채팅방 메세지 함수
function addMessage(message) {
    const ul = room.querySelector("ul");
    const li = document.createElement("li");
    li.innerText = message;
    ul.appendChild(li);
}

socket.on('connect', function() {
    // get logged in user's nickname
    const nickname = document.getElementById('nickname-input').value;

    // emit 'nickname' event to server with logged in user's nickname
    socket.emit('nickname', nickname);

    // update the 'nickname-display' element with logged in user's nickname
    document.getElementById('nickname-display').textContent = nickname;
});

// app.js
let nickname;

function setNickname(newNickname) {
    nickname = newNickname;
    document.getElementById("nickname-display").textContent = nickname;
    socket.emit("login", nickname);
}

document.getElementById("nickname-form").addEventListener("submit", (event) => {
    event.preventDefault();
    const input = document.getElementById("nickname-input");
    const newNickname = input.value;
    setNickname(newNickname);
    input.value = "";
});

document.getElementById("nickname-display").textContent = nickname;
document.addEventListener('DOMContentLoaded', () => {
    // Your code here
    const nicknameForm = document.querySelector('#nickname-form');
    const nicknameInput = document.querySelector('#nickname-input');
    const nicknameDisplay = document.querySelector('#nickname-display');

    nicknameForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const nickname = nicknameInput.value.trim();
        nicknameDisplay.textContent = `Nickname: ${nickname}`;
        socket.emit('login', nickname);
    });
});


document.querySelector("#nickname").addEventListener("submit", (e) => {
    e.preventDefault();
    const nickname = document.querySelector("#nickname input").value;
    socket.emit("change_nickname", nickname, () => {
        document.querySelector("#nick h3").textContent = nickname;
        document.querySelector("#nickname input").value = "";
    });
    document.querySelector("#nick").style.display = "none";
    document.querySelector("#welcome").style.display = "block";
});


// 메시지 입력 폼에서 입력을 받아와 서버로 새로운 메시지를 전송하는 함수
// 소켓 메시지로 전송하고, 전송이 완료된 후 화면에 메시지를 추가함
function handleMessageSubmit(event) {
    event.preventDefault();
    const input = room.querySelector("#msg input");
    const value = input.value;
    socket.emit("new_message", input.value, roomName, () => {
        addMessage(`You: ${value}`);
    });
    input.value = "";
}

function showWelcome() {
/*    const nickForm = welcome.querySelector("#nick");
    nickForm.addEventListener("submit", handleNickSubmit);*/
    const roomForm = welcome.querySelector("#room");
    roomForm.addEventListener("submit", handleRoomSubmit);
}


// 채팅방 화면을 보여주는 함수
// 기존에는 숨겨져 있던 채팅방 요소를 보이게 함
// 메시지 입력 폼의 이벤트 핸들러를 추가함
function showRoom() {
    welcome.hidden = true;
    room.hidden = false;
    const h3 = room.querySelector("h3");
    h3.innerText = `Room ${roomName}`;
    const msgForm = room.querySelector("#msg");
    msgForm.addEventListener("submit", handleMessageSubmit);
}


// 새로운 채팅방에 입장하기 위한 함수
// 채팅방 이름을 입력받아 서버로 전송하고, 채팅방 화면을 보여주는 함수를 콜백으로 전달함
function handleRoomSubmit(event) {
    event.preventDefault();
    const input = form.querySelector("input");
    socket.emit("enter_room", input.value, showRoom);
    roomName = input.value;
    input.value = "";
}

/*form.addEventListener("submit", handleRoomSubmit);*/

socket.on("welcome", (user, newCount) => {
    const h3 = room.querySelector("h3");
    h3.innerText = `Room ${roomName} (${newCount})`;
    addMessage(`${user}님이 입장하셨습니다.`);
});

socket.on("bye", (left, newCount) => {
    const h3 = room.querySelector("h3");
    h3.innerText = `Room ${roomName} (${newCount})`;
    addMessage(`${left}님이 채팅방을 나가셨습니다.`);
});

socket.on("new_message", addMessage);

socket.on("room_change", (rooms) => {
    const roomList = welcome.querySelector("ul");
    if (rooms.length === 0) {
        roomList.innerHTML = "";
        return;
    }
    rooms.forEach((room) => {
        const li = document.createElement("li");
        li.innerText = room;
        roomList.append(li);
    });
});
