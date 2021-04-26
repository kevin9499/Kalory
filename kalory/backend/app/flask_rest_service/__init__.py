import os
from flask import Flask
from flask.ext import restful
from flask.ext.pymongo import PyMongo
from flask import make_response
from bson.json_util import dumps
import logging

MONGO_URL = "mongodb://mongodb:27017/off?ssl=false"

app = Flask(__name__)

app.config['MONGO_URI'] = MONGO_URL
app.config['MONGO_DBNAME'] = 'off'

print(app.config)

log = logging.getLogger('werkzeug')
log.setLevel(logging.DEBUG)

mongo = PyMongo(app)


# ----- Output JSON function -----
def output_json(obj, code, headers=None):
	headers['Access-Control-Allow-Origin'] = '*'
	resp = make_response(dumps(obj), code)
	resp.headers.extend(headers or {})
	return resp


DEFAULT_REPRESENTATIONS = {'application/json': output_json}
api = restful.Api(app)
api.representations = DEFAULT_REPRESENTATIONS

# ----- Import all the WebServices -----
import flask_rest_service.resources_root
import flask_rest_service.resources_products
import flask_rest_service.resources_stats
