// studentdashboard.js

function logout() {
  localStorage.removeItem("student");
  window.location.href = "login.html";
}
