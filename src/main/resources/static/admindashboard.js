async function loadDashboardCounts() {
    try {
      const [pendingRes, approvedRes, rejectedRes, usersRes] = await Promise.all([
        fetch("http://localhost:8081/api/students/pending"),
        fetch("http://localhost:8081/api/students/approved"),
        fetch("http://localhost:8081/api/students/rejected"),
        fetch("http://localhost:8081/api/students/users") // you may need to implement this in backend
      ]);
  
      const pending = await pendingRes.json();
      const approved = await approvedRes.json();
      const rejected = await rejectedRes.json();
      const users = await usersRes.json();
  
      document.getElementById("pendingCount").innerText = pending.length;
      document.getElementById("approvedCount").innerText = approved.length;
      document.getElementById("rejectedCount").innerText = rejected.length;
      document.getElementById("userCount").innerText = users.length;
    } catch (error) {
      console.error("Error loading dashboard stats:", error);
    }
  }
  
  loadDashboardCounts();
  