<!DOCTYPE html>
<html lang="en">
<head>
  <title>PE Java Simple Web</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Hashing Text App</h2>
  <form class="form-horizontal" action="/javasimpleweb/echo">
    <div class="form-group">
      <label class="control-label col-sm-2" for="text">Text to Hash:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="text" placeholder="Enter any text to find MD5 or SHA Hash" name="text">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default" name="hashingAlgorithm" value="md5">MD5</button>
        <button type="submit" class="btn btn-default" name="hashingAlgorithm" value="sha">SHA</button>
      </div>
    </div>
  </form>
</div>

</body>
</html>
