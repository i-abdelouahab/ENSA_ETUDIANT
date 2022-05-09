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

    if(isset($_POST["Envoyer"])){

      $apogee=$_POST['apogee'];
      $cin=$_POST['cin'];
      $nom=$_POST['nom'];
      $email=$_POST['email'];
      $typeDemande=$_POST['demande'];
      $sql = "SELECT apogee, cin, email, fullName FROM student WHERE apogee='$apogee' and fullName='$nom' and email='$email' and cin='$cin'";
      $result = $conn->query($sql);
        if($result->num_rows > 0){
           $waiting_for_decision = ' ';
            mysqli_query($conn,"insert into gl_demandescolarite(`Email`, `fullName`, `typedemande`, `traitee`, `CIN`, `apogee`, `etat`) values ('$email','$nom','$typeDemande', 0,'$cin', $apogee, 'En attente')");
            //$query = "INSERT INTO creathive_applications VALUES (NULL,'$status','$firstname','$lastname','$email','$url')";
	//$result = mysql_query($sql) or trigger_error(mysql_error().". Query: ".$sql);
            echo '<div class="alert alert-success">
            <strong>Parfait!</strong> Votre demande est enregistrée avec succès.
          </div>';
      }
      else{
        echo '<div class="alert alert-danger">
          <strong>Attention!</strong> Les informations son incorrects !
          </div>';
      }
      /*$rechercher = mysqli_query($conn,$sql);
      $count = mysqli_num_rows($rechercher);
    while($row = mysqli_fetch_row($rechercher)){
        if($apogee!=$row[0] || $cin!=$row[1] || $email!=$row[2] || $nom!=$row[3]){
          echo '<div class="alert alert-danger">
          <strong>Attention!</strong> Les informations son incorrects !
          </div>';
          header("refresh: 3");
         }
        if($apogee=$row[0] && $cin=$row[1] && $email=$row[2] && $nom=$row[3]){
           $waiting_for_decision = ' ';
            mysqli_query($conn, "insert into gl_demandescolarite(Email, fullName, typedemande, traitee, CIN, apogee) values('$_POST[email]','$_POST[nom]','$_POST[demande]','$waiting_for_decision','$_POST[cin]','$_POST[apogee]')");
            echo '<div class="alert alert-success">
            <strong>Parfait!</strong> Votre demande est enregistrée avec succès.
          </div>';
          }    
        }*/
     }

?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Page d'acceuil</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="div1">
      <center><img src="ensate.png" alt=""></center>
          <h1>SERVICES POUR LES ETUDIANTS</h1>
    </div>
    <center><form action="index.php" method="post">
          <strong><label for="user">N° d'Apogée :</label></strong><br>
        <input type="number" id="apogee" placeholder="Code APOGEE" name="apogee" required><br>
        <strong><label for="cin">Carte d'identité/Carte de séjour :</label></strong><br>
        <input type="text" id="user" placeholder="CIN" name="cin" style="text-transform: uppercase;" required><br>
        <strong><label for="mail">Email Institutionnel :</label></strong><br>
        <input type="email" id="mail" placeholder="prenom.nom@etu.uae.ac.ma" name="email" required><br>
        <strong><label for="name">Nom complet :</label></strong><br>
        <input type="text" id="name" placeholder="NOM PRENOM" name="nom" style="text-transform: uppercase;" required><br>
          <strong><label for="demande">Type de demande :</label></strong><br>
          <input list="demande" placeholder="Veuillez choisir un document !" name="demande" required>
                  <datalist id="demande">
                       <option value="Attestation de scolarité">
                       <option value="Relevé de notes">
                       <option value="Convention de stage">
                  </datalist><br>
          <input type="submit" value="Effectuer une demande" name="Envoyer" class="ppp1"/>
      </form></center>
   
</body>
</html>