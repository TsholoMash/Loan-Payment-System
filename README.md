# loan-payment-system
Loan payment system for Radix tech assessment

### Build & Run
./mvnw clean install

./mvnw spring-boot:run

### Test APIs
curl -X POST localhost:8080/loans -H "Content-Type: application/json" -d '{"loanAmount":10000,"term":12}'

curl -X POST localhost:8080/payments -H "Content-Type: application/json" -d '{"loanId":1,"paymentAmount":2000}'

curl localhost:8080/loans/1