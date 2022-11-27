# Api-Automated-Testing

Instrucciones para correr el proyecto:
* clonar repositorio
* ir al siguiente url https://mockapi.io/projects/637a987b702b9830b9f0b747
* abrir repositorio en IDE
* ir a la clase TransactionsTest
* cargar datos en mockapi
* ejecutar los test en IDE

Final Exercise (Dev)

Create an account https://www.mockapi.io/projects and set up an endpoint for Bank transactions (all
information embedded in same API, i.e., only 1 endpoint required)
➔ Structure the project making the requests reusable, avoid to repeat code or the endpoints, use TestNG
and create a Readme.md with the specifications and steps to run the exercise and add a gitignore.
➔ For every request please make sure to include at least an assertion for the Status Code (Use POJOs to
manage response data not just the body). Please make sure you use JavaDoc.
➔ Create the following tests using the the Bank transactions endpoint:
➔ @Test 1 > Verify the Endpoint is empty (If it has any data use the DELETE request to clean and
leave it empty)
➔ @Test 2 > Initialize the POJO with 10 random data (Use the minimal Requirements). Also, make
a code verification for avoiding duplicate email accounts. Then, perform the POST request.
➔ @Test 3 > Make the GET request, asserting that there are not duplicate email accounts.
➔ @Test 4 > Add a test to update an existing AccountNumber
