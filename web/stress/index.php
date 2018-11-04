<?php 

ini_set('display_errors', 1); 
ini_set('error_reporting', E_ALL);

include('config.php');

$mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
/* проверка соединения */
if ($mysqli->connect_errno) {
    printf("Не удалось подключиться: %s\n", $mysqli->connect_error);
    exit();
}
if (!$mysqli->set_charset("utf8")) {
    printf("Ошибка при загрузке набора символов utf8: %s\n", $mysqli->error);
    exit();
} 

$myquery = $mysqli->query('select (SELECT count(*) as stress FROM popular_words WHERE stress is not null), (SELECT count(*)  FROM popular_words WHERE stress is null) from dual');
$row = $myquery->fetch_array(MYSQLI_NUM);
$progress=($row[0]/$row[1]);
echo "Progress: ".$progress."%";


$getXmlIdQuery = $mysqli->query('select w.xml_id from lemmas l, words w 
 		where l.lemma_id=w.xml_id and l.popularity > 0 
        and w.stress IS NULL
 		LIMIT 1');
$xmlIdArr = $getXmlIdQuery->fetch_array(MYSQLI_NUM);
$xmlId = $xmlIdArr[0];
echo "<br>XML_ID=".$xmlId.'<br>';
$getWordQuery = $mysqli->query('SELECT id, word,stress FROM words where xml_id='.$xmlId);
 /*while ($row = $myquery->fetch_assoc()){
	 echo $row["word"]; 
 }*/
?>

<html>
	<head>
	<title>Проставлятор ударений 2018</title>
	</head>
	<body>
		<form action="send_info.php" method="POST">
			<table>
				<tr>
					<td>
					<table>
						<tr>
							<td>Слово
							</td>
							<td>Номер<br>ударного слога
							</td>
						</tr>
						<?php while ($row = $getWordQuery->fetch_assoc()) { ?>
						<tr>
							<td><input type="text" readonly="true" value="<?php echo $row["word"]; ?>" size="20">
							</td>
							<td><input type="number" placeholder="0" name="word[<?php echo $row["id"]; ?>]" value="<?php echo $row["stress"]; ?>" min="0" max="20" size="1">
							</td>
						</tr>
						 <?php } ?>
					</table>
					</td>
					
					<td>
					</td>
				</tr>
			</table>
			 <p><input type="submit" value="Отправить"></p>
		</form>
	</body>
</html>










<?php 
/*$mtime = microtime();        //Считываем текущее время
$mtime = explode(" ", $mtime);    //Разделяем секунды и миллисекунды

// Составляем одно число из секунд и миллисекунд
// и записываем стартовое время в переменную
$tstart = $mtime[1] + $mtime[0];

define('DB_NAME', 'nklv94mail_rapgn'); 
define('DB_USER', 'nklv94mail_rapgn'); 
define('DB_HOST', 'localhost'); 
define('DB_PASSWORD', '1q3w5e2a4s6d');

$link = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD) or die ("Could not connect to MySQL");

$charset = 'utf8';
$connection_method = 'SET CHARACTER SET';

@mysql_query("{$connection_method} {$charset}", $link);

mysql_select_db (DB_NAME) or die ("Could not select database!");


    $result = mysql_query('SELECT word FROM words where xml_id=1');
	
  
while ($row = mysql_fetch_assoc($result)) {
    echo $row['word'];
}
  
 */

 
  
// Делаем все то же самое, чтобы получить текущее время
/*$mtime = microtime();
$mtime = explode(" ",$mtime);
$mtime = $mtime[1] + $mtime[0];
$totaltime = ($mtime - $tstart); //Вычисляем разницу	
  
  print 'php - ' . $totaltime ;*/


 ?>