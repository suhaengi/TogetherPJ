// 소켓 객체 생성 및 서버 연결
const socket = io("http://localhost:8085", { path: "/socket.io", transports: ["websocket"] });

// HTML 문서 내의 요소들을 가져와 변수에 저장
const welcome = document.getElementById("welcome");
const form = welcome.querySelector("form");
const room = document.getElementById("room");

// 채팅방 요소는 화면에서 숨김처리
room.hidden = true;
let roomName;

// 채팅방 메세지 함수
function addMessage(message) {
    const ul = room.querySelector("ul");
    const li = document.createElement("li");
    li.innerText = message;
    ul.appendChild(li);
}
/*
// 저장된 닉네임이 있는지 확인하고 있으면 그 값을 가져오기
function loadNickname() {
    const savedNickname = localStorage.getItem("nickname");
    if (savedNickname) {
        socket.send(makeMessage("nickname", savedNickname));
    }
}

loadNickname();

// 새로 입력된 닉네임을 로컬 스토리지에 저장
function handleNickSubmit(event) {
    event.preventDefault();
    const input = nickForm.querySelector("input");
    const nickname = input.value;
    socket.send(makeMessage("nickname", nickname));
    localStorage.setItem("nickname", nickname);
}*/

const nickNameDiv = document.getElementById("nick");
const nickNameH3 = nickNameDiv.querySelector("h3");
nickNameH3.textContent = nickname;


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
    welcome.hidden = false;
    room.hidden = true;
    const nickForm = welcome.querySelector("#nick");
    nickForm.addEventListener("submit", handleNickSubmit);
    const roomForm = welcome.querySelector("#room");
    roomForm.addEventListener("submit", handleRoomSubmit);
    loadNickname(); // 추가
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

form.addEventListener("submit", handleRoomSubmit);

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
