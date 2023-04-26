const drawStar = (target) => {
  document.querySelector(`.star span`).style.width = `${target.value * 10}%`;
};
