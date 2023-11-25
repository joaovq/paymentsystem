docker-compose down
docker build -t backend-pagnet:latest ./paymentsystemservice
docker build -t frontend-pagnet:latest ./paymentsystemfrontend
docker-compose up --build --force-recreate --remove-orphans
