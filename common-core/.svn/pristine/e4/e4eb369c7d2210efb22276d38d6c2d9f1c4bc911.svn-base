/*
 * GeodirGeocoderJS 0.0.1, a JS library  http://www.geodir.co
 * (c) 2017 Danilo Nicolas Mendoza Ricaldi
 */
(function (global, factory) {
	typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports) :
	typeof define === 'function' && define.amd ? define(['exports'], factory) :
	(factory((global.GeodirClient = global.GeodirClient || {})));
}(this, (function (exports) {
'use strict';
var URL_SERVICES = {
		SIMPLE: '/geodir.address',
		ENTITY: '/geodir.address/search'};
var SimpleClient = Geodir.Evented.extend({
	version: "0.0.1",
	/** @section
	 * 
	 */
	options: {
		textStrings: Geodir.TEXT_STRINGS,
		attribution: 'Geocoding by <a href="https://www.geodir.pe/geocoder/">Geodir</a>',
		serviceBase: 'http://geocoder.geodir.co/geocoder.api/geocoding/1.0',
	},
	/**
	 * @section
	 * 
	 */
	initialize: function (token,options) {
		if (token) {
			this.token=token;
		}else {
			console.warn('ingrese token');
			return;
		}
		Geodir.Util.setOptions(this, options);
	},	
	geocodeForward:function(data){
		var comp = this;
		let url= comp.options.serviceBase+ URL_SERVICES.ENTITY;
		return new Promise(function (resolve, reject) {
			comp._geocodeForward(url,comp.token,data,resolve,reject);
		});
	},
	/**
	 * @param {Object}  ubigeo if addres is nul ubigeo must to be an Object like {ubigeo:15001,address:'Av aramburu 456'}
	 * @param {Object}  address 
	 * 
	 */
	geocodeAddressForward:function(ubigeo,address){
		if (!this.token) {
			console.warn('ingrese token');
			return;
		}
		let _data=[];
		let _data_one={};
		if (address) {
			_data_one={
				ubigeo:ubigeo,
				address:address
			}
		}else {
			_data_one=ubigeo;
		}
		_data.push(_data_one);
		var comp = this;
		let url= comp.options.serviceBase+ URL_SERVICES.SIMPLE;
		return new Promise(function (resolve, reject) {
			comp._geocodeForward(url,comp.token,_data,resolve,reject);
		});
	},
	/**
	 * @param {Object}  data array of address
	 * 
	 */
	geocodeAddressForwardBatch:function(data){
		if (!this.token) {
			console.warn('ingrese token');
			return;
		}
		if (!Array.isArray(data)) {
			console.warn('ingrese un array');
			return;
		}
		let _data=data;
		var comp = this;
		let url= comp.options.serviceBase+ URL_SERVICES.SIMPLE;
		return new Promise(function (resolve, reject) {
			comp._geocodeForward(url,comp.token,_data,resolve,reject);
		});
	},
	_geocodeForward: function (url,token,data,resolve,reject) {
		let _data = JSON.stringify(data);
		var self = this;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
		    if (this.readyState == 4) {
		    	if (this.status === 200) {
		    		resolve(JSON.parse(this.responseText));
		    	} else {
		    		var errorMessage;
		    		switch (this.status) {
			          case 403:
			            errorMessage = self.options.textStrings['ERROR_403'];
			            break;
			          case 404:
			            errorMessage = self.options.textStrings['ERROR_404'];
			            break;
			          case 408:
			            errorMessage = self.options.textStrings['ERROR_408'];
			            break;
			          case 429:
			            errorMessage = self.options.textStrings['ERROR_429'];
			            break;
			          case 500:
			            errorMessage = self.options.textStrings['ERROR_500'];
			            break;
			          case 502:
			            errorMessage = self.options.textStrings['ERROR_502'];
			            break;
			          default:
			            errorMessage = self.options.textStrings['ERROR_DEFAULT'];
			            break;
			        }
		    		reject(errorMessage);
		    	}
		    }
		  };
		  xhttp.open("PUT",url, true);
		  xhttp.setRequestHeader("Content-type", "application/json; charset=utf-8");
		  xhttp.setRequestHeader('Authorization', 'bearer '+token);
		  xhttp.send(_data);
	},
});

/**
* Instantiates a SimpleClient object
* options object.
*/ 
function simpleClient(options) {
	return new SimpleClient(options);
}
var oldGeodirClient = window.GeodirClient;
function noConflict() {
	window.GeodirClient = oldGeodirClient;
	return this;
}
// Always export us to window global (see #2364)
window.GeodirClient = SimpleClient;
})));
// # sourceMappingURL=geodir-src.js.map