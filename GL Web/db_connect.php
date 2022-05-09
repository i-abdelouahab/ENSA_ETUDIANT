<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "serviceetudiant";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
echo '<div class="alert alert-success">  DB DED </div>';
  die("Connection failed: " . $conn->connect_error);
}
?>