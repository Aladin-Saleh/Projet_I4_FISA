# Documentation et utilisation 


## JSONHandler


```JSON
{
  "userId": 1,
  "id": 1,
  "title": "delectus aut autem",
  "completed": false
}
```

```JAVA
JSONHandler jsonHandler = new JSONHandler();
System.out.println(jsonHandler.readJSON(response).get("title"));
```