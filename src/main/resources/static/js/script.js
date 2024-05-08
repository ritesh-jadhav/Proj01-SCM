console.log("script loaded");

let currentTheme = getTheme();
document.addEventListener("DOMContentLoaded", function () {
  changeTheme();
});

function changeTheme() {
  // for the first time when page loads
  changePageTheme(currentTheme, currentTheme);
  //
  const changeThemeButton = document.querySelector("#change_theme_button");

  changeThemeButton.querySelector("span").textContent =
    currentTheme == "light" ? "Dark" : "Light";
  const oldTheme = currentTheme;
  changeThemeButton.addEventListener("click", (event) => {
    //theme toggler
    if (currentTheme == "light") {
      currentTheme = "dark";
    } else {
      currentTheme = "light";
    }
    changePageTheme(currentTheme, oldTheme);
  });
}

function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}

function changePageTheme(theme, oldTheme) {
  setTheme(currentTheme);
  document.querySelector("html").classList.remove(oldTheme);
  //add new class
  document.querySelector("html").classList.add(theme);
  document
    .querySelector("#change_theme_button")
    .querySelector("span").textContent = theme == "light" ? "dark" : "light";
}
