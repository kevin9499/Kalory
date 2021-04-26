FROM python:3-alpine

RUN mkdir -p /app/api

WORKDIR /app/api

COPY . .

RUN pip install -r requirements.txt
RUN sleep 20

ENTRYPOINT ["python", "runApiRESTServer.py"]