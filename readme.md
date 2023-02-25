Consumer app runs on port `8080`

Producer app runs on port `8081`

### To test:

1. Run postgres docker container:

   ```docker-compose up```
2. Run consumer and producer apps
3. Use curl
    ```
    curl --location --request POST 'http://localhost:8081/uploadFile'
    ```