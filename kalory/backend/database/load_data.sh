cd /database
tar -xzvf data.tar.gz
mongorestore --host mongodb -d off -c products dump/off/products.bson