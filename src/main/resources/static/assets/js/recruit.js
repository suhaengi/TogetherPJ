function categoryChange(e) {
  const state = document.getElementById("state");

  const asia = ["일본", "중국", "베트남", "대만", "홍콩", "태국"];
  const africa = ["가나", "모로코", "이집트"];
  const europe = ["프랑스", "이탈리아", "체코", "영국", "스위스"];
  const oceania = ["뉴질랜드", "오스트레일리아"];
  const southA = ["브라질", "콜롬비아", "아르헨티나"];
  const northA = ["캐나다", "미국", "멕시코"];

  if (e.value == "asia") {
    add = asia;
  } else if (e.value == "africa") {
    add = africa;
  } else if (e.value == "europe") {
    add = europe;
  } else if (e.value == "oceania") {
    add = oceania;
  } else if (e.value == "southA") {
    add = southA;
  } else if (e.value == "northA") {
    add = northA;
  }

  state.options.length = 1;

  for (property in add) {
    let opt = document.createElement("option");
    opt.value = add[property];
    opt.innerHTML = add[property];
    state.appendChild(opt);
  }
}
