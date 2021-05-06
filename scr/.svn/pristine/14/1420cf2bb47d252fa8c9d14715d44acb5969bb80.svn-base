(function() {
  'use strict';
  /**
   * tabs
   *
   * @description The Tabs component.
   * @param {Object} options The options hash
   */
  var tabs = function(options) {
    var el = document.querySelector(options.el);
    var tabNavigationLinks = el.querySelectorAll(options.tabNavigationLinks);
    var tabContentContainers = el.querySelectorAll(options.tabContentContainers);
    var activeIndex = 0;
    var initCalled = false;
    /**
     * init
     *
     * @description Initializes the component by removing the no-js class from
     *   the component, and attaching event listeners to each of the nav items.
     *   Returns nothing.
     */
    var init = function() {
      if (!initCalled) {
        initCalled = true;
        el.classList.remove('no-js');
        
        for (var i = 0; i < tabNavigationLinks.length; i++) {
          var link = tabNavigationLinks[i];
          handleClick(link, i);
        }
      }
    };

    /**
     * handleClick
     *
     * @description Handles click event listeners on each of the links in the
     *   tab navigation. Returns nothing.
     * @param {HTMLElement} link The link to listen for events on
     * @param {Number} index The index of that link
     */
    var handleClick = function(link, index) {
      link.addEventListener('click', function(e) {
        e.preventDefault();
        goToTab(index);
      });
    };

    /**
     * goToTab
     *
     * @description Goes to a specific tab based on index. Returns nothing.
     * @param {Number} index The index of the tab to go to
     */
    var goToTab = function(index) {
      if (index !== activeIndex && index >= 0 && index <= tabNavigationLinks.length) {
        tabNavigationLinks[activeIndex].classList.remove('is-active');
        tabNavigationLinks[index].classList.add('is-active');
        tabContentContainers[activeIndex].classList.remove('is-active');
        tabContentContainers[index].classList.add('is-active');
        activeIndex = index;
      }
    };

    /**
     * Returns init and goToTab
     */
    return {
      init: init,
      goToTab: goToTab
    };

  };

  /**
   * Attach to global namespace
   */
  window.tabs = tabs;
})();
/*
 * GeodirJS 0.0.1, a JS library for interactive maps. http://www.geodir.co
 * (c) 2017 Danilo Nicolas Mendoza Ricaldi
 */
(function (global, factory) {
	typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports) :
	typeof define === 'function' && define.amd ? define(['exports'], factory) :
	(factory((global.Geodir = global.Geodir || {})));
}(this, (function (exports) { 'use strict';

var version = "0.0.1";
/*
 * @namespace Util
 * 
 * Various utility functions, used by Geodir internally.
 */

// @function extend(dest: Object, src?: Object): Object
function extend(dest) {
	var i, j, len, src;
	for (j = 1, len = arguments.length; j < len; j++) {
		src = arguments[j];
		for (i in src) {
			dest[i] = src[i];
		}
	}
	return dest;
}

// @function create(proto: Object, properties?: Object): Object
// Compatibility polyfill for
// [Object.create](https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global_Objects/Object/create)
var create = Object.create || (function () {
	function F() {}
	return function (proto) {
		F.prototype = proto;
		return new F();
	};
})();

// @function bind(fn: Function, Ã¢â‚¬Â¦): Function
// Returns a new function bound to the arguments passed, like
// [Function.prototype.bind](https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global_Objects/Function/bind).
// Has a `L.bind()` shortcut.
function bind(fn, obj) {
	var slice = Array.prototype.slice;

	if (fn.bind) {
		return fn.bind.apply(fn, slice.call(arguments, 1));
	}

	var args = slice.call(arguments, 2);

	return function () {
		return fn.apply(obj, args.length ? args.concat(slice.call(arguments)) : arguments);
	};
}

// @property lastId: Number
// Last unique ID used by [`stamp()`](#util-stamp)
var lastId = 0;

// @function stamp(obj: Object): Number
// Returns the unique ID of an object, assiging it one if it doesn't have it.
function stamp(obj) {
	/* eslint-disable */
	obj._geodir_id = obj._geodir_id || ++lastId;
	return obj._geodir_id;
	/* eslint-enable */
}

// @function throttle(fn: Function, time: Number, context: Object): Function
// Returns a function which executes function `fn` with the given scope
// `context`
// (so that the `this` keyword refers to `context` inside `fn`'s code). The
// function
// `fn` will be called no more than one time per given amount of `time`. The
// arguments
// received by the bound function will be any arguments passed when binding the
// function, followed by any arguments passed when invoking the bound function.
// Has an `Geodir.throttle` shortcut.
function throttle(fn, time, context) {
	var lock, args, wrapperFn, later;

	later = function () {
		// reset lock and call if queued
		lock = false;
		if (args) {
			wrapperFn.apply(context, args);
			args = false;
		}
	};

	wrapperFn = function () {
		if (lock) {
			// called too soon, queue to call later
			args = arguments;

		} else {
			// call and lock until later
			fn.apply(context, arguments);
			setTimeout(later, time);
			lock = true;
		}
	};

	return wrapperFn;
}

// @function wrapNum(num: Number, range: Number[], includeMax?: Boolean): Number
// Returns the number `num` modulo `range` in such a way so it lies within
// `range[0]` and `range[1]`. The returned value will be always smaller than
// `range[1]` unless `includeMax` is set to `true`.
function wrapNum(x, range, includeMax) {
	var max = range[1],
	    min = range[0],
	    d = max - min;
	return x === max && includeMax ? x : ((x - min) % d + d) % d + min;
}

// @function falseFn(): Function
// Returns a function which always returns `false`.
function falseFn() { return false; }

// @function formatNum(num: Number, digits?: Number): Number
// Returns the number `num` rounded to `digits` decimals, or to 5 decimals by
// default.
function formatNum(num, digits) {
	var pow = Math.pow(10, digits || 5);
	return Math.round(num * pow) / pow;
}

// @function trim(str: String): String
// Compatibility polyfill for
// [String.prototype.trim](https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global_Objects/String/Trim)
function trim(str) {
	return str.trim ? str.trim() : str.replace(/^\s+|\s+$/g, '');
}

// @function splitWords(str: String): String[]
// Trims and splits the string on whitespace and returns the array of parts.
function splitWords(str) {
	return trim(str).split(/\s+/);
}

// @function setOptions(obj: Object, options: Object): Object
// Merges the given properties to the `options` of the `obj` object, returning
// the resulting options. See `Class options`. Has an `L.setOptions` shortcut.
function setOptions(obj, options) {
	if (!obj.hasOwnProperty('options')) {
		obj.options = obj.options ? create(obj.options) : {};
	}
	for (var i in options) {
		obj.options[i] = options[i];
	}
	return obj.options;
}

// @function getParamString(obj: Object, existingUrl?: String, uppercase?:
// Boolean): String
// Converts an object into a parameter URL string, e.g. `{a: "foo", b: "bar"}`
// translates to `'?a=foo&b=bar'`. If `existingUrl` is set, the parameters will
// be appended at the end. If `uppercase` is `true`, the parameter names will
// be uppercased (e.g. `'?A=foo&B=bar'`)
function getParamString(obj, existingUrl, uppercase) {
	var params = [];
	for (var i in obj) {
		params.push(encodeURIComponent(uppercase ? i.toUpperCase() : i) + '=' + encodeURIComponent(obj[i]));
	}
	return ((!existingUrl || existingUrl.indexOf('?') === -1) ? '?' : '&') + params.join('&');
}

var templateRe = /\{ *([\w_\-]+) *\}/g;

// @function template(str: String, data: Object): String
// Simple templating facility, accepts a template string of the form `'Hello
// {a}, {b}'`
// and a data object like `{a: 'foo', b: 'bar'}`, returns evaluated string
// `('Hello foo, bar')`. You can also specify functions instead of strings for
// data values Ã¢â‚¬â€ they will be evaluated passing `data` as an argument.
function template(str, data) {
	return str.replace(templateRe, function (str, key) {
		var value = data[key];

		if (value === undefined) {
			throw new Error('No value provided for variable ' + str);

		} else if (typeof value === 'function') {
			value = value(data);
		}
		return value;
	});
}

// @function isArray(obj): Boolean
// Compatibility polyfill for
// [Array.isArray](https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global_Objects/Array/isArray)
var isArray = Array.isArray || function (obj) {
	return (Object.prototype.toString.call(obj) === '[object Array]');
};

// @function indexOf(array: Array, el: Object): Number
// Compatibility polyfill for
// [Array.prototype.indexOf](https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global_Objects/Array/indexOf)
function indexOf(array, el) {
	for (var i = 0; i < array.length; i++) {
		if (array[i] === el) { return i; }
	}
	return -1;
}

// inspired by
// http://paulirish.com/2011/requestanimationframe-for-smart-animating/

var Util = (Object.freeze || Object)({
	extend: extend,
	create: create,
	bind: bind,
	lastId: lastId,
	stamp: stamp,
	throttle: throttle,
	wrapNum: wrapNum,
	falseFn: falseFn,
	formatNum: formatNum,
	trim: trim,
	splitWords: splitWords,
	setOptions: setOptions,
	getParamString: getParamString,
	template: template,
	isArray: isArray,
	indexOf: indexOf,
});

//@namespace SVG; @section
//There are several static functions which can be called without instantiating
//L.SVG:

//@function create(name: String): SVGElement
//Returns a instance of
//[SVGElement](https://developer.mozilla.org/docs/Web/API/SVGElement),
//corresponding to the class name passed. For example, using 'line' will return
//an instance of
//[SVGLineElement](https://developer.mozilla.org/docs/Web/API/SVGLineElement).
function svgCreate(name) {
	return document.createElementNS('http://www.w3.org/2000/svg', name);
}


/*
 * @namespace Browser @aka L.Browser
 * 
 * A namespace with static properties for browser/feature detection used by
 * Geodir internally.
 * 
 * @example
 * 
 * ```js if (L.Browser.ielt9) { alert('Upgrade your browser, dude!'); } ```
 */

var style$1 = document.documentElement.style;

// @property ie: Boolean; `true` for all Internet Explorer versions (not Edge).
var ie = 'ActiveXObject' in window;

// @property ielt9: Boolean; `true` for Internet Explorer versions less than 9.
var ielt9 = ie && !document.addEventListener;

// @property edge: Boolean; `true` for the Edge web browser.
var edge = 'msLaunchUri' in navigator && !('documentMode' in document);

// @property webkit: Boolean;
// `true` for webkit-based browsers like Chrome and Safari (including mobile
// versions).
var webkit = userAgentContains('webkit');

// @property android: Boolean
// `true` for any browser running on an Android platform.
var android = userAgentContains('android');

// @property android23: Boolean; `true` for browsers running on Android 2 or
// Android 3.
var android23 = userAgentContains('android 2') || userAgentContains('android 3');

// @property opera: Boolean; `true` for the Opera browser
var opera = !!window.opera;

// @property chrome: Boolean; `true` for the Chrome browser.
var chrome = userAgentContains('chrome');

// @property gecko: Boolean; `true` for gecko-based browsers like Firefox.
var gecko = userAgentContains('gecko') && !webkit && !opera && !ie;

// @property safari: Boolean; `true` for the Safari browser.
var safari = !chrome && userAgentContains('safari');

var phantom = userAgentContains('phantom');

// @property opera12: Boolean
// `true` for the Opera browser supporting CSS transforms (version 12 or later).
var opera12 = 'OTransition' in style$1;

// @property win: Boolean; `true` when the browser is running in a Windows
// platform
var win = navigator.platform.indexOf('Win') === 0;

// @property ie3d: Boolean; `true` for all Internet Explorer versions supporting
// CSS transforms.
var ie3d = ie && ('transition' in style$1);

// @property webkit3d: Boolean; `true` for webkit-based browsers supporting CSS
// transforms.
var webkit3d = ('WebKitCSSMatrix' in window) && ('m11' in new window.WebKitCSSMatrix()) && !android23;

// @property gecko3d: Boolean; `true` for gecko-based browsers supporting CSS
// transforms.
var gecko3d = 'MozPerspective' in style$1;

// @property any3d: Boolean
// `true` for all browsers supporting CSS transforms.
var any3d = !window.L_DISABLE_3D && (ie3d || webkit3d || gecko3d) && !opera12 && !phantom;

// @property mobile: Boolean; `true` for all browsers running in a mobile
// device.
var mobile = typeof orientation !== 'undefined' || userAgentContains('mobile');

// @property mobileWebkit: Boolean; `true` for all webkit-based browsers in a
// mobile device.
var mobileWebkit = mobile && webkit;

// @property mobileWebkit3d: Boolean
// `true` for all webkit-based browsers in a mobile device supporting CSS
// transforms.
var mobileWebkit3d = mobile && webkit3d;

// @property msPointer: Boolean
// `true` for browsers implementing the Microsoft touch events model (notably
// IE10).
var msPointer = !window.PointerEvent && window.MSPointerEvent;

// @property pointer: Boolean
// `true` for all browsers supporting [pointer
// events](https://msdn.microsoft.com/en-us/library/dn433244%28v=vs.85%29.aspx).
var pointer = !!(window.PointerEvent || msPointer);

// @property touch: Boolean
// `true` for all browsers supporting [touch
// events](https://developer.mozilla.org/docs/Web/API/Touch_events).
// This does not necessarily mean that the browser is running in a computer with
// a touchscreen, it only means that the browser is capable of understanding
// touch events.
var touch = !window.L_NO_TOUCH && (pointer || 'ontouchstart' in window ||
		(window.DocumentTouch && document instanceof window.DocumentTouch));

// @property mobileOpera: Boolean; `true` for the Opera browser in a mobile
// device.
var mobileOpera = mobile && opera;

// @property mobileGecko: Boolean
// `true` for gecko-based browsers running in a mobile device.
var mobileGecko = mobile && gecko;

// @property retina: Boolean
// `true` for browsers on a high-resolution "retina" screen.
var retina = (window.devicePixelRatio || (window.screen.deviceXDPI / window.screen.logicalXDPI)) > 1;


// @property canvas: Boolean
// `true` when the browser supports
// [`<canvas>`](https://developer.mozilla.org/docs/Web/API/Canvas_API).
var canvas = (function () {
	return !!document.createElement('canvas').getContext;
}());

// @property svg: Boolean
// `true` when the browser supports
// [SVG](https://developer.mozilla.org/docs/Web/SVG).
var svg = !!(document.createElementNS && svgCreate('svg').createSVGRect);

// @property vml: Boolean
// `true` if the browser supports
// [VML](https://en.wikipedia.org/wiki/Vector_Markup_Language).
var vml = !svg && (function () {
	try {
		var div = document.createElement('div');
		div.innerHTML = '<v:shape adj="1"/>';

		var shape = div.firstChild;
		shape.style.behavior = 'url(#default#VML)';

		return shape && (typeof shape.adj === 'object');

	} catch (e) {
		return false;
	}
}());


function userAgentContains(str) {
	return navigator.userAgent.toLowerCase().indexOf(str) >= 0;
}


var Browser = (Object.freeze || Object)({
	ie: ie,
	ielt9: ielt9,
	edge: edge,
	webkit: webkit,
	android: android,
	android23: android23,
	opera: opera,
	chrome: chrome,
	gecko: gecko,
	safari: safari,
	phantom: phantom,
	opera12: opera12,
	win: win,
	ie3d: ie3d,
	webkit3d: webkit3d,
	gecko3d: gecko3d,
	any3d: any3d,
	mobile: mobile,
	mobileWebkit: mobileWebkit,
	mobileWebkit3d: mobileWebkit3d,
	msPointer: msPointer,
	pointer: pointer,
	touch: touch,
	mobileOpera: mobileOpera,
	mobileGecko: mobileGecko,
	retina: retina,
	canvas: canvas,
	svg: svg,
	vml: vml
});

/*
 * Extends L.DomEvent to provide touch support for Internet Explorer and
 * Windows-based devices.
 */


var POINTER_DOWN =   msPointer ? 'MSPointerDown'   : 'pointerdown';
var POINTER_MOVE =   msPointer ? 'MSPointerMove'   : 'pointermove';
var POINTER_UP =     msPointer ? 'MSPointerUp'     : 'pointerup';
var POINTER_CANCEL = msPointer ? 'MSPointerCancel' : 'pointercancel';
var TAG_WHITE_LIST = ['INPUT', 'SELECT', 'OPTION'];
var _pointers = {};
var _pointerDocListener = false;

// DomEvent.DoubleTap needs to know about this
var _pointersCount = 0;

// Provides a touch events wrapper for (ms)pointer events.
// ref http://www.w3.org/TR/pointerevents/
// https://www.w3.org/Bugs/Public/show_bug.cgi?id=22890


function removePointerListener(obj, type, id) {
	var handler = obj['_geodir_' + type + id];

	if (type === 'touchstart') {
		obj.removeEventListener(POINTER_DOWN, handler, false);

	} else if (type === 'touchmove') {
		obj.removeEventListener(POINTER_MOVE, handler, false);

	} else if (type === 'touchend') {
		obj.removeEventListener(POINTER_UP, handler, false);
		obj.removeEventListener(POINTER_CANCEL, handler, false);
	}

	return this;
}


/*
 * Extends the event handling code with double tap support for mobile browsers.
 */

var _touchstart = msPointer ? 'MSPointerDown' : pointer ? 'pointerdown' : 'touchstart';
var _touchend = msPointer ? 'MSPointerUp' : pointer ? 'pointerup' : 'touchend';
var _pre = '_geodir_';

// inspired by Zepto touch code by Thomas Fuchs
function addDoubleTapListener(obj, handler, id) {
	var last, touch$$1,
	    doubleTap = false,
	    delay = 250;

	function onTouchStart(e) {
		var count;

		if (pointer) {
			if ((!edge) || e.pointerType === 'mouse') { return; }
			count = _pointersCount;
		} else {
			count = e.touches.length;
		}

		if (count > 1) { return; }

		var now = Date.now(),
		    delta = now - (last || now);

		touch$$1 = e.touches ? e.touches[0] : e;
		doubleTap = (delta > 0 && delta <= delay);
		last = now;
	}

	function onTouchEnd(e) {
		if (doubleTap && !touch$$1.cancelBubble) {
			if (pointer) {
				if ((!edge) || e.pointerType === 'mouse') { return; }
				// work around .type being readonly with MSPointer* events
				var newTouch = {},
				    prop, i;

				for (i in touch$$1) {
					prop = touch$$1[i];
					newTouch[i] = prop && prop.bind ? prop.bind(touch$$1) : prop;
				}
				touch$$1 = newTouch;
			}
			touch$$1.type = 'dblclick';
			handler(touch$$1);
			last = null;
		}
	}

	obj[_pre + _touchstart + id] = onTouchStart;
	obj[_pre + _touchend + id] = onTouchEnd;
	obj[_pre + 'dblclick' + id] = handler;

	obj.addEventListener(_touchstart, onTouchStart, false);
	obj.addEventListener(_touchend, onTouchEnd, false);

	// On some platforms (notably, chrome<55 on win10 + touchscreen + mouse),
	// the browser doesn't fire touchend/pointerup events but does fire
	// native dblclicks. See #4127.
	// Edge 14 also fires native dblclicks, but only for pointerType mouse, see
	// #5180.
	obj.addEventListener('dblclick', handler, false);

	return this;
}

function removeDoubleTapListener(obj, id) {
	var touchstart = obj[_pre + _touchstart + id],
	    touchend = obj[_pre + _touchend + id],
	    dblclick = obj[_pre + 'dblclick' + id];

	obj.removeEventListener(_touchstart, touchstart, false);
	obj.removeEventListener(_touchend, touchend, false);
	if (!edge) {
		obj.removeEventListener('dblclick', dblclick, false);
	}

	return this;
}

/*
 * @namespace DomEvent Utility functions to work with the [DOM
 * events](https://developer.mozilla.org/docs/Web/API/Event), used by Geodir
 * internally.
 */

// Inspired by John Resig, Dean Edwards and YUI addEvent implementations.

// @function on(el: HTMLElement, types: String, fn: Function, context?: Object):
// this
// Adds a listener function (`fn`) to a particular DOM event type of the
// element `el`. You can optionally specify the context of the listener
// (object the `this` keyword will point to). You can also pass several
// space-separated types (e.g. `'click dblclick'`).

// @alternative
// @function on(el: HTMLElement, eventMap: Object, context?: Object): this
// Adds a set of type/listener pairs, e.g. `{click: onClick, mousemove:
// onMouseMove}`
function on(obj, types, fn, context) {

	if (typeof types === 'object') {
		for (var type in types) {
			addOne(obj, type, types[type], fn);
		}
	} else {
		types = splitWords(types);

		for (var i = 0, len = types.length; i < len; i++) {
			addOne(obj, types[i], fn, context);
		}
	}

	return this;
}

var eventsKey = '_geodir_events';

// @function off(el: HTMLElement, types: String, fn: Function, context?:
// Object): this
// Removes a previously added listener function. If no function is specified,
// it will remove all the listeners of that particular DOM event from the
// element.
// Note that if you passed a custom context to on, you must pass the same
// context to `off` in order to remove the listener.

// @alternative
// @function off(el: HTMLElement, eventMap: Object, context?: Object): this
// Removes a set of type/listener pairs, e.g. `{click: onClick, mousemove:
// onMouseMove}`

// @alternative
// @function off(el: HTMLElement): this
// Removes all known event listeners
function off(obj, types, fn, context) {

	if (typeof types === 'object') {
		for (var type in types) {
			removeOne(obj, type, types[type], fn);
		}
	} else if (types) {
		types = splitWords(types);

		for (var i = 0, len = types.length; i < len; i++) {
			removeOne(obj, types[i], fn, context);
		}
	} else {
		for (var j in obj[eventsKey]) {
			removeOne(obj, j, obj[eventsKey][j]);
		}
		delete obj[eventsKey];
	}
}

function addOne(obj, type, fn, context) {
	var id = type + stamp(fn) + (context ? '_' + stamp(context) : '');

	if (obj[eventsKey] && obj[eventsKey][id]) { return this; }

	var handler = function (e) {
		return fn.call(context || obj, e || window.event);
	};

	var originalHandler = handler;

	if (pointer && type.indexOf('touch') === 0) {
		// Needs DomEvent.Pointer.js
		addPointerListener(obj, type, handler, id);

	} else if (touch && (type === 'dblclick') && addDoubleTapListener &&
	           !(pointer && chrome)) {
		// Chrome >55 does not need the synthetic dblclicks from
		// addDoubleTapListener
		// See #5180
		addDoubleTapListener(obj, handler, id);

	} else if ('addEventListener' in obj) {

		if (type === 'mousewheel') {
			obj.addEventListener('onwheel' in obj ? 'wheel' : 'mousewheel', handler, false);

		} else if ((type === 'mouseenter') || (type === 'mouseleave')) {
			handler = function (e) {
				e = e || window.event;
				if (isExternalTarget(obj, e)) {
					originalHandler(e);
				}
			};
			obj.addEventListener(type === 'mouseenter' ? 'mouseover' : 'mouseout', handler, false);

		} else {
			if (type === 'click' && android) {
				handler = function (e) {
					filterClick(e, originalHandler);
				};
			}
			obj.addEventListener(type, handler, false);
		}

	} else if ('attachEvent' in obj) {
		obj.attachEvent('on' + type, handler);
	}

	obj[eventsKey] = obj[eventsKey] || {};
	obj[eventsKey][id] = handler;
}

function removeOne(obj, type, fn, context) {

	var id = type + stamp(fn) + (context ? '_' + stamp(context) : ''),
	    handler = obj[eventsKey] && obj[eventsKey][id];

	if (!handler) { return this; }

	if (pointer && type.indexOf('touch') === 0) {
		removePointerListener(obj, type, id);

	} else if (touch && (type === 'dblclick') && removeDoubleTapListener) {
		removeDoubleTapListener(obj, id);

	} else if ('removeEventListener' in obj) {

		if (type === 'mousewheel') {
			obj.removeEventListener('onwheel' in obj ? 'wheel' : 'mousewheel', handler, false);

		} else {
			obj.removeEventListener(
				type === 'mouseenter' ? 'mouseover' :
				type === 'mouseleave' ? 'mouseout' : type, handler, false);
		}

	} else if ('detachEvent' in obj) {
		obj.detachEvent('on' + type, handler);
	}

	obj[eventsKey][id] = null;
}

// @function stopPropagation(ev: DOMEvent): this
// Stop the given event from propagation to parent elements. Used inside the
// listener functions:
// ```js
// L.DomEvent.on(div, 'click', function (ev) {
// L.DomEvent.stopPropagation(ev);
// });
// ```
function stopPropagation(e) {

	if (e.stopPropagation) {
		e.stopPropagation();
	} else if (e.originalEvent) {  // In case of Geodir event.
		e.originalEvent._stopped = true;
	} else {
		e.cancelBubble = true;
	}
	skipped(e);

	return this;
}

// @function disableScrollPropagation(el: HTMLElement): this
// Adds `stopPropagation` to the element's `'mousewheel'` events (plus browser
// variants).
function disableScrollPropagation(el) {
	return addOne(el, 'mousewheel', stopPropagation);
}

// @function disableClickPropagation(el: HTMLElement): this
// Adds `stopPropagation` to the element's `'click'`, `'doubleclick'`,
// `'mousedown'` and `'touchstart'` events (plus browser variants).
function disableClickPropagation(el) {
	on(el, 'mousedown touchstart dblclick', stopPropagation);
	addOne(el, 'click', fakeStop);
	return this;
}

// @function preventDefault(ev: DOMEvent): this
// Prevents the default action of the DOM Event `ev` from happening (such as
// following a link in the href of the a element, or doing a POST request
// with page reload when a `<form>` is submitted).
// Use it inside listener functions.
function preventDefault(e) {
	if (e.preventDefault) {
		e.preventDefault();
	} else {
		e.returnValue = false;
	}
	return this;
}

// @function stop(ev): this
// Does `stopPropagation` and `preventDefault` at the same time.
function stop(e) {
	preventDefault(e);
	stopPropagation(e);
	return this;
}

// Chrome on Win scrolls double the pixels as in other platforms (see #4538),
// and Firefox scrolls device pixels, not CSS pixels
var wheelPxFactor =
	(win && chrome) ? 2 * window.devicePixelRatio :
	gecko ? window.devicePixelRatio : 1;

// @function getWheelDelta(ev: DOMEvent): Number
// Gets normalized wheel delta from a mousewheel DOM event, in vertical
// pixels scrolled (negative if scrolling down).
// Events from pointing devices without precise scrolling are mapped to
// a best guess of 60 pixels.
function getWheelDelta(e) {
	return (edge) ? e.wheelDeltaY / 2 : // Don't trust window-geometry-based
										// delta
	       (e.deltaY && e.deltaMode === 0) ? -e.deltaY / wheelPxFactor : // Pixels
	       (e.deltaY && e.deltaMode === 1) ? -e.deltaY * 20 : // Lines
	       (e.deltaY && e.deltaMode === 2) ? -e.deltaY * 60 : // Pages
	       (e.deltaX || e.deltaZ) ? 0 :	// Skip horizontal/depth wheel events
	       e.wheelDelta ? (e.wheelDeltaY || e.wheelDelta) / 2 : // Legacy IE
																// pixels
	       (e.detail && Math.abs(e.detail) < 32765) ? -e.detail * 20 : // Legacy
																		// Moz
																		// lines
	       e.detail ? e.detail / -32765 * 60 : // Legacy Moz pages
	       0;
}

var skipEvents = {};

function fakeStop(e) {
	// fakes stopPropagation by setting a special event flag, checked/reset with
	// skipped(e)
	skipEvents[e.type] = true;
}

function skipped(e) {
	var events = skipEvents[e.type];
	// reset when checking, as it's only used in map container and propagates
	// outside of the map
	skipEvents[e.type] = false;
	return events;
}

// check if element really left/entered the event target (for
// mouseenter/mouseleave)
function isExternalTarget(el, e) {

	var related = e.relatedTarget;

	if (!related) { return true; }

	try {
		while (related && (related !== el)) {
			related = related.parentNode;
		}
	} catch (err) {
		return false;
	}
	return (related !== el);
}

var lastClick;

// this is a horrible workaround for a bug in Android where a single touch
// triggers two click events
function filterClick(e, handler) {
	var timeStamp = (e.timeStamp || (e.originalEvent && e.originalEvent.timeStamp)),
	    elapsed = lastClick && (timeStamp - lastClick);

	// are they closer together than 500ms yet more than 100ms?
	// Android typically triggers them ~300ms apart while multiple listeners
	// on the same event should be triggered far faster;
	// or check if click is simulated on the element, and if it is, reject any
	// non-simulated events

	if ((elapsed && elapsed > 100 && elapsed < 500) || (e.target._simulatedClick && !e._simulated)) {
		stop(e);
		return;
	}
	lastClick = timeStamp;

	handler(e);
}




var DomEvent = (Object.freeze || Object)({
	on: on,
	off: off,
	stopPropagation: stopPropagation,
	disableScrollPropagation: disableScrollPropagation,
	disableClickPropagation: disableClickPropagation,
	preventDefault: preventDefault,
	stop: stop,
	getWheelDelta: getWheelDelta,
	fakeStop: fakeStop,
	skipped: skipped,
	isExternalTarget: isExternalTarget,
	addListener: on,
	removeListener: off
});

/*
 * @namespace DomUtil
 * 
 * Utility functions to work with the
 * [DOM](https://developer.mozilla.org/docs/Web/API/Document_Object_Model) tree,
 * used by Geodir internally.
 * 
 * Most functions expecting or returning a `HTMLElement` also work for SVG
 * elements. The only difference is that classes refer to CSS classes in HTML
 * and SVG classes in SVG.
 */


// @property TRANSFORM: String
// Vendor-prefixed transform style name (e.g. `'webkitTransform'` for WebKit).
var TRANSFORM = testProp(
	['transform', 'WebkitTransform', 'OTransform', 'MozTransform', 'msTransform']);

// webkitTransition comes first because some browser versions that drop vendor
// prefix don't do
// the same for the transitionend event, in particular the Android 4.1 stock
// browser

// @property TRANSITION: String
// Vendor-prefixed transition style name.
var TRANSITION = testProp(
	['webkitTransition', 'transition', 'OTransition', 'MozTransition', 'msTransition']);

// @property TRANSITION_END: String
// Vendor-prefixed transitionend event name.
var TRANSITION_END =
	TRANSITION === 'webkitTransition' || TRANSITION === 'OTransition' ? TRANSITION + 'End' : 'transitionend';


// @function get(id: String|HTMLElement): HTMLElement
// Returns an element given its DOM id, or returns the element itself
// if it was passed directly.
function get(id) {
	return typeof id === 'string' ? document.getElementById(id) : id;
}

// @function getStyle(el: HTMLElement, styleAttrib: String): String
// Returns the value for a certain style attribute on an element,
// including computed values or values set through CSS.
function getStyle(el, style) {
	var value = el.style[style] || (el.currentStyle && el.currentStyle[style]);

	if ((!value || value === 'auto') && document.defaultView) {
		var css = document.defaultView.getComputedStyle(el, null);
		value = css ? css[style] : null;
	}
	return value === 'auto' ? null : value;
}

// @function create(tagName: String, className?: String, container?:
// HTMLElement): HTMLElement
// Creates an HTML element with `tagName`, sets its class to `className`, and
// optionally appends it to `container` element.
function create$1(tagName, className, container) {
	var el = document.createElement(tagName);
	el.className = className || '';

	if (container) {
		container.appendChild(el);
	}
	return el;
}

// @function remove(el: HTMLElement)
// Removes `el` from its parent element
function remove(el) {
	var parent = el.parentNode;
	if (parent) {
		parent.removeChild(el);
	}
}

// @function empty(el: HTMLElement)
// Removes all of `el`'s children elements from `el`
function empty(el) {
	while (el.firstChild) {
		el.removeChild(el.firstChild);
	}
}

// @function toFront(el: HTMLElement)
// Makes `el` the last child of its parent, so it renders in front of the other
// children.
function toFront(el) {
	var parent = el.parentNode;
	if (parent.lastChild !== el) {
		parent.appendChild(el);
	}
}

// @function toBack(el: HTMLElement)
// Makes `el` the first child of its parent, so it renders behind the other
// children.
function toBack(el) {
	var parent = el.parentNode;
	if (parent.firstChild !== el) {
		parent.insertBefore(el, parent.firstChild);
	}
}

// @function hasClass(el: HTMLElement, name: String): Boolean
// Returns `true` if the element's class attribute contains `name`.
function hasClass(el, name) {
	if (el.classList !== undefined) {
		return el.classList.contains(name);
	}
	var className = getClass(el);
	return className.length > 0 && new RegExp('(^|\\s)' + name + '(\\s|$)').test(className);
}

// @function addClass(el: HTMLElement, name: String)
// Adds `name` to the element's class attribute.
function addClass(el, name) {
	if (el.classList !== undefined) {
		var classes = splitWords(name);
		for (var i = 0, len = classes.length; i < len; i++) {
			el.classList.add(classes[i]);
		}
	} else if (!hasClass(el, name)) {
		var className = getClass(el);
		setClass(el, (className ? className + ' ' : '') + name);
	}
}

// @function removeClass(el: HTMLElement, name: String)
// Removes `name` from the element's class attribute.
function removeClass(el, name) {
	if (el.classList !== undefined) {
		el.classList.remove(name);
	} else {
		setClass(el, trim((' ' + getClass(el) + ' ').replace(' ' + name + ' ', ' ')));
	}
}

// @function setClass(el: HTMLElement, name: String)
// Sets the element's class.
function setClass(el, name) {
	if (el.className.baseVal === undefined) {
		el.className = name;
	} else {
		// in case of SVG element
		el.className.baseVal = name;
	}
}

// @function getClass(el: HTMLElement): String
// Returns the element's class.
function getClass(el) {
	return el.className.baseVal === undefined ? el.className : el.className.baseVal;
}

// @function testProp(props: String[]): String|false
// Goes through the array of style names and returns the first name
// that is a valid style name for an element. If no such name is found,
// it returns false. Useful for vendor-prefixed styles like `transform`.
function testProp(props) {
	var style = document.documentElement.style;

	for (var i = 0; i < props.length; i++) {
		if (props[i] in style) {
			return props[i];
		}
	}
	return false;
}


// @function disableTextSelection()
// Prevents the user from generating `selectstart` DOM events, usually generated
// when the user drags the mouse through a page with text. Used internally
// by Geodir to override the behaviour of any click-and-drag interaction on
// the map. Affects drag interactions on the whole document.

// @function enableTextSelection()
// Cancels the effects of a previous
// [`L.DomUtil.disableTextSelection`](#domutil-disabletextselection).
var disableTextSelection;
var enableTextSelection;
var _userSelect;
if ('onselectstart' in document) {
	disableTextSelection = function () {
		on(window, 'selectstart', preventDefault);
	};
	enableTextSelection = function () {
		off(window, 'selectstart', preventDefault);
	};
} else {
	var userSelectProperty = testProp(
		['userSelect', 'WebkitUserSelect', 'OUserSelect', 'MozUserSelect', 'msUserSelect']);

	disableTextSelection = function () {
		if (userSelectProperty) {
			var style = document.documentElement.style;
			_userSelect = style[userSelectProperty];
			style[userSelectProperty] = 'none';
		}
	};
	enableTextSelection = function () {
		if (userSelectProperty) {
			document.documentElement.style[userSelectProperty] = _userSelect;
			_userSelect = undefined;
		}
	};
}

var _outlineElement;
var _outlineStyle;
// @function preventOutline(el: HTMLElement)
// Makes the [outline](https://developer.mozilla.org/docs/Web/CSS/outline)
// of the element `el` invisible. Used internally by Geodir to prevent
// focusable elements from displaying an outline when the user performs a
// drag interaction on them.
function preventOutline(element) {
	while (element.tabIndex === -1) {
		element = element.parentNode;
	}
	if (!element.style) { return; }
	restoreOutline();
	_outlineElement = element;
	_outlineStyle = element.style.outline;
	element.style.outline = 'none';
	on(window, 'keydown', restoreOutline);
}

// @function restoreOutline()
// Cancels the effects of a previous [`L.DomUtil.preventOutline`]().
function restoreOutline() {
	if (!_outlineElement) { return; }
	_outlineElement.style.outline = _outlineStyle;
	_outlineElement = undefined;
	_outlineStyle = undefined;
	off(window, 'keydown', restoreOutline);
}


var DomUtil = (Object.freeze || Object)({
	TRANSFORM: TRANSFORM,
	TRANSITION: TRANSITION,
	TRANSITION_END: TRANSITION_END,
	get: get,
	getStyle: getStyle,
	create: create$1,
	remove: remove,
	empty: empty,
	toFront: toFront,
	toBack: toBack,
	hasClass: hasClass,
	addClass: addClass,
	removeClass: removeClass,
	setClass: setClass,
	getClass: getClass,
	testProp: testProp,
	disableTextSelection: disableTextSelection,
	enableTextSelection: enableTextSelection,
	preventOutline: preventOutline,
	restoreOutline: restoreOutline
});

//@class Class
//@aka L.Class

//@section
//@uninheritable

//Thanks to John Resig and Dean Edwards for inspiration!
function Class() {}

Class.extend = function (props) {

	// @function extend(props: Object): Function
	// [Extends the current class](#class-inheritance) given the properties to
	// be included.
	// Returns a Javascript function that is a class constructor (to be called
	// with `new`).
	var NewClass = function () {
		// call the constructor
		if (this.initialize) {
			this.initialize.apply(this, arguments);
		}
		// call all constructor hooks
		this.callInitHooks();
	};

	var parentProto = NewClass.__super__ = this.prototype;

	var proto = create(parentProto);
	proto.constructor = NewClass;

	NewClass.prototype = proto;

	// inherit parent's statics
	for (var i in this) {
		if (this.hasOwnProperty(i) && i !== 'prototype' && i !== '__super__') {
			NewClass[i] = this[i];
		}
	}

	// mix static properties into the class
	if (props.statics) {
		extend(NewClass, props.statics);
		delete props.statics;
	}

	// mix includes into the prototype
	if (props.includes) {
		checkDeprecatedMixinEvents(props.includes);
		extend.apply(null, [proto].concat(props.includes));
		delete props.includes;
	}

	// merge options
	if (proto.options) {
		props.options = extend(create(proto.options), props.options);
	}

	// mix given properties into the prototype
	extend(proto, props);

	proto._initHooks = [];

	// add method for calling all hooks
	proto.callInitHooks = function () {
		if (this._initHooksCalled) { return; }

		if (parentProto.callInitHooks) {
			parentProto.callInitHooks.call(this);
		}

		this._initHooksCalled = true;

		for (var i = 0, len = proto._initHooks.length; i < len; i++) {
			proto._initHooks[i].call(this);
		}
	};

	return NewClass;
};


// @function include(properties: Object): this
// [Includes a mixin](#class-includes) into the current class.
Class.include = function (props) {
	extend(this.prototype, props);
	return this;
};

// @function mergeOptions(options: Object): this
// [Merges `options`](#class-options) into the defaults of the class.
Class.mergeOptions = function (options) {
	extend(this.prototype.options, options);
	return this;
};

// @function addInitHook(fn: Function): this
// Adds a [constructor hook](#class-constructor-hooks) to the class.
Class.addInitHook = function (fn) { // (Function) || (String, args...)
	var args = Array.prototype.slice.call(arguments, 1);

	var init = typeof fn === 'function' ? fn : function () {
		this[fn].apply(this, args);
	};

	this.prototype._initHooks = this.prototype._initHooks || [];
	this.prototype._initHooks.push(init);
	return this;
};

function checkDeprecatedMixinEvents(includes) {
	if (!L || !L.Mixin) { return; }

	includes = isArray(includes) ? includes : [includes];

	for (var i = 0; i < includes.length; i++) {
		if (includes[i] === L.Mixin.Events) {
			console.warn('Deprecated include of L.Mixin.Events: ' +
				'this property will be removed in future releases, ' +
				'please inherit from L.Evented instead.', new Error().stack);
		}
	}
}

/*
 * @class Evented @aka L.Evented @inherits Class
 * 
 * A set of methods shared between event-powered classes (like `Map` and
 * `Marker`). Generally, events allow you to execute some function when
 * something happens with an object (e.g. the user clicks on the map, causing
 * the map to fire `'click'` event).
 * 
 * @example
 * 
 * ```js map.on('click', function(e) { alert(e.latlng); } ); ```
 * 
 * Geodir deals with event listeners by reference, so if you want to add a
 * listener and then remove it, define it as a function:
 * 
 * ```js function onClick(e) { ... }
 * 
 * map.on('click', onClick); map.off('click', onClick); ```
 */

var Events = {
	/*
	 * @method on(type: String, fn: Function, context?: Object): this Adds a
	 * listener function (`fn`) to a particular event type of the object. You
	 * can optionally specify the context of the listener (object the this
	 * keyword will point to). You can also pass several space-separated types
	 * (e.g. `'click dblclick'`).
	 * 
	 * @alternative @method on(eventMap: Object): this Adds a set of
	 * type/listener pairs, e.g. `{click: onClick, mousemove: onMouseMove}`
	 */
	on: function (types, fn, context) {

		// types can be a map of types/handlers
		if (typeof types === 'object') {
			for (var type in types) {
				// we don't process space-separated events here for performance;
				// it's a hot path since Layer uses the on(obj) syntax
				this._on(type, types[type], fn);
			}

		} else {
			// types can be a string of space-separated words
			types = splitWords(types);

			for (var i = 0, len = types.length; i < len; i++) {
				this._on(types[i], fn, context);
			}
		}

		return this;
	},
	/*
	 * @method off(type: String, fn?: Function, context?: Object): this Removes
	 * a previously added listener function. If no function is specified, it
	 * will remove all the listeners of that particular event from the object.
	 * Note that if you passed a custom context to `on`, you must pass the same
	 * context to `off` in order to remove the listener.
	 * 
	 * @alternative @method off(eventMap: Object): this Removes a set of
	 * type/listener pairs.
	 * 
	 * @alternative @method off: this Removes all listeners to all events on the
	 * object.
	 */
	off: function (types, fn, context) {

		if (!types) {
			// clear all listeners if called without arguments
			delete this._events;

		} else if (typeof types === 'object') {
			for (var type in types) {
				this._off(type, types[type], fn);
			}

		} else {
			types = splitWords(types);

			for (var i = 0, len = types.length; i < len; i++) {
				this._off(types[i], fn, context);
			}
		}

		return this;
	},

	// attach listener (without syntactic sugar now)
	_on: function (type, fn, context) {
		this._events = this._events || {};

		/* get/init listeners for type */
		var typeListeners = this._events[type];
		if (!typeListeners) {
			typeListeners = [];
			this._events[type] = typeListeners;
		}

		if (context === this) {
			// Less memory footprint.
			context = undefined;
		}
		var newListener = {fn: fn, ctx: context},
		    listeners = typeListeners;

		// check if fn already there
		for (var i = 0, len = listeners.length; i < len; i++) {
			if (listeners[i].fn === fn && listeners[i].ctx === context) {
				return;
			}
		}

		listeners.push(newListener);
	},

	_off: function (type, fn, context) {
		var listeners,
		    i,
		    len;

		if (!this._events) { return; }

		listeners = this._events[type];

		if (!listeners) {
			return;
		}

		if (!fn) {
			// Set all removed listeners to noop so they are not called if
			// remove happens in fire
			for (i = 0, len = listeners.length; i < len; i++) {
				listeners[i].fn = falseFn;
			}
			// clear all listeners for a type if function isn't specified
			delete this._events[type];
			return;
		}

		if (context === this) {
			context = undefined;
		}

		if (listeners) {

			// find fn and remove it
			for (i = 0, len = listeners.length; i < len; i++) {
				var l = listeners[i];
				if (l.ctx !== context) { continue; }
				if (l.fn === fn) {

					// set the removed listener to noop so that's not called if
					// remove happens in fire
					l.fn = falseFn;

					if (this._firingCount) {
						/* copy array in case events are being fired */
						this._events[type] = listeners = listeners.slice();
					}
					listeners.splice(i, 1);

					return;
				}
			}
		}
	},

	// @method fire(type: String, data?: Object, propagate?: Boolean): this
	// Fires an event of the specified type. You can optionally provide an data
	// object Ã¢â‚¬â€ the first argument of the listener function will contain its
	// properties. The event can optionally be propagated to event parents.
	fire: function (type, data, propagate) {
		if (!this.listens(type, propagate)) { return this; }

		var event = extend({}, data, {type: type, target: this});

		if (this._events) {
			var listeners = this._events[type];

			if (listeners) {
				this._firingCount = (this._firingCount + 1) || 1;
				for (var i = 0, len = listeners.length; i < len; i++) {
					var l = listeners[i];
					l.fn.call(l.ctx || this, event);
				}

				this._firingCount--;
			}
		}

		if (propagate) {
			// propagate the event to parents (set with addEventParent)
			this._propagateEvent(event);
		}

		return this;
	},

	// @method listens(type: String): Boolean
	// Returns `true` if a particular event type has any listeners attached to
	// it.
	listens: function (type, propagate) {
		var listeners = this._events && this._events[type];
		if (listeners && listeners.length) { return true; }

		if (propagate) {
			// also check parents for listeners if event propagates
			for (var id in this._eventParents) {
				if (this._eventParents[id].listens(type, propagate)) { return true; }
			}
		}
		return false;
	},

	// @method once(Ã¢â‚¬Â¦): this
	// Behaves as [`on(Ã¢â‚¬Â¦)`](#evented-on), except the listener will only get
	// fired once and then removed.
	once: function (types, fn, context) {

		if (typeof types === 'object') {
			for (var type in types) {
				this.once(type, types[type], fn);
			}
			return this;
		}

		var handler = bind(function () {
			this
			    .off(types, fn, context)
			    .off(types, handler, context);
		}, this);

		// add a listener that's executed once and removed after that
		return this
		    .on(types, fn, context)
		    .on(types, handler, context);
	},

	// @method addEventParent(obj: Evented): this
	// Adds an event parent - an `Evented` that will receive propagated events
	addEventParent: function (obj) {
		this._eventParents = this._eventParents || {};
		this._eventParents[stamp(obj)] = obj;
		return this;
	},

	// @method removeEventParent(obj: Evented): this
	// Removes an event parent, so it will stop receiving propagated events
	removeEventParent: function (obj) {
		if (this._eventParents) {
			delete this._eventParents[stamp(obj)];
		}
		return this;
	},

	_propagateEvent: function (e) {
		for (var id in this._eventParents) {
			this._eventParents[id].fire(e.type, extend({layer: e.target}, e), true);
		}
	}
};

//aliases; we should ditch those eventually

//@method addEventListener(Ã¢â‚¬Â¦): this
//Alias to [`on(Ã¢â‚¬Â¦)`](#evented-on)
Events.addEventListener = Events.on;

//@method removeEventListener(Ã¢â‚¬Â¦): this
//Alias to [`off(Ã¢â‚¬Â¦)`](#evented-off)

//@method clearAllEventListeners(Ã¢â‚¬Â¦): this
//Alias to [`off()`](#evented-off)
Events.removeEventListener = Events.clearAllEventListeners = Events.off;

//@method addOneTimeEventListener(Ã¢â‚¬Â¦): this
//Alias to [`once(Ã¢â‚¬Â¦)`](#evented-once)
Events.addOneTimeEventListener = Events.once;

//@method fireEvent(Ã¢â‚¬Â¦): this
//Alias to [`fire(Ã¢â‚¬Â¦)`](#evented-fire)
Events.fireEvent = Events.fire;

//@method hasEventListeners(Ã¢â‚¬Â¦): Boolean
//Alias to [`listens(Ã¢â‚¬Â¦)`](#evented-listens)
Events.hasEventListeners = Events.listens;

var Evented = Class.extend(Events);

//@function get(id: String|HTMLElement): HTMLElement
//Returns an element given its DOM id, or returns the element itself
//if it was passed directly.
function get(id) {
	return typeof id === 'string' ? document.getElementById(id) : id;
}
/*
 * @class Map @aka L.Map @inherits Evented
 * 
 * The central class of the API Ã¢â‚¬â€ it is used to create a map on a page and
 * manipulate it.
 * 
 * @example
 * 
 * ```js // initialize the map on the "map" div with a given center and zoom var
 * map = L.map('map', { center: [51.505, -0.09], zoom: 13 }); ```
 * 
 */
var Container = Evented.extend({
	options: {
		layers: [],
		serviceBase: 'http://localhost:8082/geocoder.api/geocoding/1.0',
	},
	token:null,
	initialize: function (id, token, options) { // (HTMLElement or String, Object)
		if (token!=null) {
			this.token=token;
		}else{
			console.error('Ingrese TOKEN');
			return;
		}
		options = setOptions(this, options);
		
		this._initContainer(id);
		this._initLayout();
		this._initEvents();
		this._handlers = [];
		this._layers = {};
		this._zoomBoundLayers = {};
		this._sizeChanged = true;
		this.callInitHooks();
		this._loaded = true;
		this._addLayers(this.options.layers);
	},
	_initContainer: function (id) {
		var container = this._container = get(id);
		if (!container) {
			throw new Error('Map container not found.');
		} else if (container._geodir_id) {
			throw new Error('Map container is already initialized.');
		}
		this._containerId = stamp(container);
	},
	_initEvents: function (remove$$1) {
		this._targets = {};
		this._targets[stamp(this._container)] = this;

		var onOff = remove$$1 ? off : on;



		if (this.options.trackResize) {
			onOff(window, 'resize', this._onResize, this);
		}

		if (any3d && this.options.transform3DLimit) {
			(remove$$1 ? this.off : this.on).call(this, 'moveend', this._onMoveEnd);
		}
	},

	_initLayout: function () {
		var container = this._container;
		addClass(container, 'geodir-container' +
			(touch ? ' geodir-touch' : '') +
			(retina ? ' geodir-retina' : '') +
			(ielt9 ? ' geodir-oldie' : '') +
			(safari ? ' geodir-safari' : '') +
			(this._fadeAnimated ? ' geodir-fade-anim' : ''));
		this._initPanes();
		if (this._initControlPos) {
			this._initControlPos();
		}
	},
	getPane: function (pane) {
		return typeof pane === 'string' ? this._panes[pane] : pane;
	},
	_initPanes: function () {
		var panes = this._panes = {};
		this._paneRenderers = {};
		this._mapPane = this.createPane('mapPane', this._container);
		var paneservice = this.createPane('services');
		var serviceMain = create$1('main',  'o-main',paneservice);
		var serviceContainer = create$1('div','o-container',serviceMain);
		
		var serviceSection = this.createPane('services-contend',serviceContainer);
		
		addClass(serviceSection, 'o-section');
		
		var serviceContainerfull = this.createPane('services-container',serviceSection);
		var paneservicecontend = this.createPane('services-header',serviceContainerfull);
		serviceContainerfull.id = "geodirTabs";
		addClass(serviceContainerfull, 'c-tabs');
		addClass(serviceContainerfull, 'no-js');
		addClass(paneservicecontend, 'c-tabs-nav');
		
	},
	createPane: function (name, container) {
		var className = 'geodir-pane' + (name ? ' geodir-' + name.replace('Pane', '') + '-pane' : ''),
		    pane = create$1('div', className, container || this._mapPane);

		if (name) {
			this._panes[name] = pane;
		}
		return pane;
	},
	

	
	// @method whenReady(fn: Function, context?: Object): this
	// Runs the given function `fn` when the map gets initialized with
	// a view (center and zoom) and at least one layer, or immediately
	// if it's already initialized, optionally passing a function context.
	whenReady: function (callback, context) {
		if (this._loaded) {
			callback.call(context || this, {target: this});
		} else {
			this.on('load', callback, context);
		}
		return this;
	},
});

//@section

//@factory L.map(id: String, options?: Map options)
//Instantiates a map object given the DOM ID of a `<div>` element
//and optionally an object literal with `Map options`.
//
//@alternative
//@factory L.map(el: HTMLElement, options?: Map options)
//Instantiates a map object given an instance of a `<div>` HTML element
//and optionally an object literal with `Map options`.
function createContainer(id,token, options) {
	return new Container(id,token, options);
}

var MINIMUM_INPUT_LENGTH_FOR_AUTOCOMPLETE = 1;

//Text strings in this geocoder.
var TEXT_STRINGS = {
  'INPUT_PLACEHOLDER': 'Search',
  'INPUT_TITLE_ATTRIBUTE': 'Search',
  'RESET_TITLE_ATTRIBUTE': 'Reset',
  'NO_RESULTS': 'No results were found.',
  // Error codes.
  // https://mapzen.com/documentation/search/http-status-codes/
  'ERROR_403': 'A valid API key is needed for this search feature.',
  'ERROR_404': 'The search service cannot be found. :-(',
  'ERROR_408': 'The search service took too long to respond. Try again in a second.',
  'ERROR_429': 'There were too many requests. Try again in a second.',
  'ERROR_500': 'The search service is not working right now. Please try again later.',
  'ERROR_502': 'Connection lost. Please try again later.',
  // Unhandled error code
  'ERROR_DEFAULT': 'The search service is having problems :-('
};

var API_RATE_LIMIT = 250;

var Service = Evented.extend({

	// Classes extending `L.Layer` will inherit the following options:
	options: {
		// @option pane: String = 'overlayPane'
		// By default the layer will be added to the map's [overlay
		// pane](#map-overlaypane). Overriding this option will cause the layer
		// to be placed on another pane by default.
		pane: 'overlayPane',
		// @option serviceBase: String = 'http://www.geodir.pe/geocoder.api/geocoding/1.0'
		// String to be shown in the attribution control, describes the layer
		// data, e.g. "http://www.geodir.pe/geocoder.api/geocoding/1.0".
		bubblingMouseEvents: true,
		// @option SimpleService: String = 'Service'
		// When `true`, a mouse event on this marker will trigger the same event
		// on the map
		// (unless [`L.DomEvent.stopPropagation`](#domevent-stoppropagation) is
		// used).
		label: 'Service',
		attribution: 'Geocoding by <a href="https://geocoder.geodir.co/geocoder/">Geodir</a>',
		bounds: false,
	    focus: true,
	    layers: null,
	    panToPoint: true,
	    pointIcon: true, // 'images/point_icon.png',
	    fullWidth: 650,
	    markers: true,
	    overrideBbox: false,
	    expanded: false,
	    autocomplete: true,
	    place: false,
	    textStrings: TEXT_STRINGS
	},

	/*
	 * @section Classes extending `L.Layer` will inherit the following methods:
	 * 
	 * @method addTo(map: Map): this Adds the layer to the given map
	 */
	addTo: function (map) {
		map.addLayer(this);
		return this;
	},

	// @method remove: this
	// Removes the layer from the map it is currently active on.
	remove: function () {
		return this.removeFrom(this._map || this._mapToAdd);
	},

	// @method removeFrom(map: Map): this
	// Removes the layer from the given map
	removeFrom: function (obj) {
		if (obj) {
			obj.removeLayer(this);
		}
		return this;
	},

	// @method getPane(name? : String): HTMLElement
	// Returns the `HTMLElement` representing the named pane on the map. If
	// `name` is omitted, returns the pane for this layer.
	getPane: function (name) {
		return this._map.getPane(name ? (this.options[name] || name) : this.options.pane);
	},

	// @method getAttribution: String
	// Used by the `attribution control`, returns the [attribution
	// option](#gridlayer-attribution).
	getAttribution: function () {
		return this.options.attribution;
	},
	_layerAdd: function (e) {
		var map = e.target;
		// check in case layer gets added and then removed before the map is
		// ready
		if (!map.hasLayer(this)) { return; }

		this._map = map;
		this._zoomAnimated = map._zoomAnimated;

		if (this.getEvents) {
			var events = this.getEvents();
			map.on(events, this);
			this.once('remove', function () {
				map.off(events, this);
			}, this);
		}
		var count = Object.keys(this._map._layers).length;
		var container = create$1('div', 'c-tab');
		if(count==1){
			addClass(container,"is-active");
		}
		var _container = this.tab_content =  create$1('div', 'c-tab__content',container);
		// ading service container
		var service_container = this._container = this.onAdd(map);
		_container.appendChild(service_container);
		
		map.getPane('services-container').appendChild(container);
		
		if (this.getAttribution && map.attributionControl) {
			map.attributionControl.addAttribution(this.getAttribution());
		}

		this.fire('add');
		map.fire('layeradd', {layer: this});
	},
	hasLayer: function (layer) {
		return !!layer && (stamp(layer) in this._inputs);
	},
	whenReady: function (callback, context) {
		if (this._loaded) {
			callback.call(context || this, {target: this});
		} else {
			this.on('load', callback, context);
		}
		return this;
	}
});

/*
 * @section Extension methods @uninheritable
 * 
 * @section Methods for Layers and Controls
 */
Container.include({
	// @method addLayer(layer: Layer): this
	// Adds the given layer to the map
	addLayer: function (layer) {
		if(this.token==null){
			return;
		}
		var id = stamp(layer);
		if (this._layers[id]) { return this; }
		this._layers[id] = layer;

		layer._mapToAdd = this;

		if (layer.beforeAdd) {
			layer.beforeAdd(this);
		}

		this.whenReady(layer._layerAdd, layer);
		var myTabs = tabs({
		      el: '#geodirTabs',
		      tabNavigationLinks: '.c-tabs-nav__link',
		      tabContentContainers: '.c-tab'
		});
		myTabs.init();
		return this;
	},

	// @method removeLayer(layer: Layer): this
	// Removes the given layer from the map.
	removeLayer: function (layer) {
		var id = stamp(layer);

		if (!this._layers[id]) { return this; }

		if (this._loaded) {
			layer.onRemove(this);
		}

		if (layer.getAttribution && this.attributionControl) {
			this.attributionControl.removeAttribution(layer.getAttribution());
		}

		delete this._layers[id];

		if (this._loaded) {
			this.fire('layerremove', {layer: layer});
			layer.fire('remove');
		}

		layer._map = layer._mapToAdd = null;

		return this;
	},

	// @method hasLayer(layer: Layer): Boolean
	// Returns `true` if the given layer is currently added to the map
	hasLayer: function (layer) {
		return !!layer && (stamp(layer) in this._layers);
	},

	/*
	 * @method eachLayer(fn: Function, context?: Object): this Iterates over the
	 * layers of the map, optionally specifying context of the iterator
	 * function. ``` map.eachLayer(function(layer){ layer.bindPopup('Hello');
	 * }); ```
	 */
	eachLayer: function (method, context) {
		for (var i in this._layers) {
			method.call(context, this._layers[i]);
		}
		return this;
	},

	_addLayers: function (layers) {
		layers = layers ? (isArray(layers) ? layers : [layers]) : [];

		for (var i = 0, len = layers.length; i < len; i++) {
			this.addLayer(layers[i]);
		}
	},

});

var TabNav = Class.extend({

	initialize: function (options) {
		setOptions(this, options);
	},
	
	createTabNav: function (oldIcon) {
		return this._createTabNav('c-tabs-nav__link', oldIcon);
	},
	_createTabNav: function (className, oldIcon) {
		var options = this.options;
		var nav = this._createNav(oldIcon? oldIcon : null);
		addClass(nav,className);
		var el_i = create$1('i',  options.icon , nav);
		var el_span = create$1('span','', nav);
		el_span.innerHTML  =options.label;
		return nav;
	},
	_createNav: function (el) {
		el = el || document.createElement('a');
		return el;
	},
});


// @factory L.icon(options: Icon options)
// Creates an icon instance with the given options.
function tabNav(options) {
	return new TabNav(options);
}

var TabNavDefault = TabNav.extend({
	options: {
		icon:  'default-service',
		label: 'Service'
	},
	_getIconUrl: function (name) {
		if (!PanelDefault.imagePath) {
			PanelDefault.imagePath = this._detectIconPath();
		}
		// @option imagePath: String
		// `Icon.Default` will try to auto-detect the absolute location of the
		// blue icon images. If you are placing these images in a non-standard
		// way, set this option to point to the right absolute path.
		return (this.options.imagePath || PanelDefault.imagePath) + Panel.prototype._getIconUrl.call(this, name);
	},
});

/*
 * @class Marker @inherits Interactive layer @aka L.Marker L.Marker is used to
 * display clickable/draggable icons on the map. Extends `Layer`.
 * 
 * @example
 * 
 * ```js L.marker([50.5, 30.5]).addTo(map); ```
 */

function throttle (func, wait, options) {
	  var context, args, result;
	  var timeout = null;
	  var previous = 0;
	  if (!options) options = {};
	  var later = function () {
	    previous = options.leading === false ? 0 : new Date().getTime();
	    timeout = null;
	    result = func.apply(context, args);
	    if (!timeout) context = args = null;
	  };
	  return function () {
	    var now = new Date().getTime();
	    if (!previous && options.leading === false) previous = now;
	    var remaining = wait - (now - previous);
	    context = this;
	    args = arguments;
	    if (remaining <= 0 || remaining > wait) {
	      if (timeout) {
	        clearTimeout(timeout);
	        timeout = null;
	      }
	      previous = now;
	      result = func.apply(context, args);
	      if (!timeout) context = args = null;
	    } else if (!timeout && options.trailing !== false) {
	      timeout = setTimeout(later, remaining);
	    }
	    return result;
	  };
	}
function corslite(url, callback, cors, token) {
    var sent = false;

    if (typeof window.XMLHttpRequest === 'undefined') {
        return callback(Error('Browser not supported'));
    }

    if (typeof cors === 'undefined') {
        var m = url.match(/^\s*https?:\/\/[^\/]*/);
        cors = m && (m[0] !== location.protocol + '//' + location.hostname +
                (location.port ? ':' + location.port : ''));
    }

    var x = new window.XMLHttpRequest();

    function isSuccessful(status) {
        return status >= 200 && status < 300 || status === 304;
    }

    if (cors && !('withCredentials' in x)) {
        // IE8-9
        x = new window.XDomainRequest();

        // Ensure callback is never called synchronously, i.e., before
        // x.send() returns (this has been observed in the wild).
        // See https://github.com/mapbox/mapbox.js/issues/472
        var original = callback;
        callback = function() {
            if (sent) {
                original.apply(this, arguments);
            } else {
                var that = this, args = arguments;
                setTimeout(function() {
                    original.apply(that, args);
                }, 0);
            }
        }
    }

    function loaded() {
        if (
            // XDomainRequest
            x.status === undefined ||
            // modern browsers
            isSuccessful(x.status)) callback.call(x, null, x);
        else callback.call(x, x, null);
    }

    // Both `onreadystatechange` and `onload` can fire. `onreadystatechange`
    // has [been supported for longer](http://stackoverflow.com/a/9181508/229001).
    if ('onload' in x) {
        x.onload = loaded;
    } else {
        x.onreadystatechange = function readystate() {
            if (x.readyState === 4) {
                loaded();
            }
        };
    }

    // Call the callback with the XMLHttpRequest object as an error and prevent
    // it from ever being called again by reassigning it to `noop`
    x.onerror = function error(evt) {
        // XDomainRequest provides no evt parameter
        callback.call(this, evt || true, null);
        callback = function() { };
    };

    // IE9 must have onprogress be set to a unique function.
    x.onprogress = function() { };

    x.ontimeout = function(evt) {
        callback.call(this, evt, null);
        callback = function() { };
    };

    x.onabort = function(evt) {
        callback.call(this, evt, null);
        callback = function() { };
    };

    // GET is the only supported HTTP Verb by XDomainRequest and is the
    // only one supported here.
    x.open('GET', url, true);
    x.setRequestHeader('Authorization', 'bearer '+token);
    // Send the request. Sending data is not supported.
    x.send(null);
    sent = true;

    return x;
}

function corslitePOST(url, callback, cors, token,data) {
    var sent = false;

    if (typeof window.XMLHttpRequest === 'undefined') {
        return callback(Error('Browser not supported'));
    }

    if (typeof cors === 'undefined') {
        var m = url.match(/^\s*https?:\/\/[^\/]*/);
        cors = m && (m[0] !== location.protocol + '//' + location.hostname +
                (location.port ? ':' + location.port : ''));
    }

    var x = new window.XMLHttpRequest();

    function isSuccessful(status) {
        return status >= 200 && status < 300 || status === 304;
    }

    if (cors && !('withCredentials' in x)) {
        // IE8-9
        x = new window.XDomainRequest();

        // Ensure callback is never called synchronously, i.e., before
        // x.send() returns (this has been observed in the wild).
        // See https://github.com/mapbox/mapbox.js/issues/472
        var original = callback;
        callback = function() {
            if (sent) {
                original.apply(this, arguments);
            } else {
                var that = this, args = arguments;
                setTimeout(function() {
                    original.apply(that, args);
                }, 0);
            }
        }
    }

    function loaded() {
        if (
            // XDomainRequest
            x.status === undefined ||
            // modern browsers
            isSuccessful(x.status)) {
        	callback.call(x, null, x);
        }
        else{
        	callback.call(x, x, null);
        } 
    }

    // Both `onreadystatechange` and `onload` can fire. `onreadystatechange`
    // has [been supported for longer](http://stackoverflow.com/a/9181508/229001).
    if ('onload' in x) {
        x.onload = loaded;
    } else {
        x.onreadystatechange = function readystate() {
            if (x.readyState === 4) {
                loaded();
            }
        };
    }

    // Call the callback with the XMLHttpRequest object as an error and prevent
    // it from ever being called again by reassigning it to `noop`
    x.onerror = function error(evt) {
        // XDomainRequest provides no evt parameter
        callback.call(this, evt || true, null);
        callback = function() { };
    };

    // IE9 must have onprogress be set to a unique function.
    x.onprogress = function() { };

    x.ontimeout = function(evt) {
        callback.call(this, evt, null);
        callback = function() { };
    };

    x.onabort = function(evt) {
        callback.call(this, evt, null);
        callback = function() { };
    };

    // GET is the only supported HTTP Verb by XDomainRequest and is the
    // only one supported here.
    x.open('POST', url, true);
    //x.setRequestHeader('contentType',"application/json; charset=utf-8");
    x.setRequestHeader("Content-type", "application/json; charset=utf-8");
    x.setRequestHeader('Authorization', 'bearer '+token);
    // Send the request. Sending data is not supported.
    x.send(data);
    sent = true;
   
    return x;
}

function escapeRegExp(str) {
	  return String(str).replace(/[-[\]/{}()*+?.\\^$|]/g, '\\$&');
}

var Autocomplete = Evented.extend({

	// Classes extending `L.Layer` will inherit the following options:
	options: {
		autocomplete: true,
		placeholder: null,
		textStrings: TEXT_STRINGS,
		resource:null,
	},
	/*
	 * @section Classes extending `L.Layer` will inherit the following methods:
	 * 
	 * @method addTo(map: Map): this Adds the layer to the given map
	 */
	addTo: function (container) {
		container.addAutocomplete(this);
		return this;
	},
	
	// @method remove: this
	// Removes the layer from the map it is currently active on.
	remove: function () {
		return this.removeFrom(this._map || this._mapToAdd);
	},
	
	changeResource:function(res){
		this.options.resource = res;
	},
	
	defaultOnResponce:function(result){
		console.log(result);
	},

	// @method removeFrom(map: Map): this
	// Removes the layer from the given map
	removeFrom: function (obj) {
		if (obj) {
			obj.removeLayer(this);
		}
		return this;
	},
	_imputAdd: function (e) {
		var map = e.target;
		// check in case layer gets added and then removed before the map is
		// ready
		if (!map.hasLayer(this)) { return; }

		this._map = map;

		var service_container = this._container = this.onAdd(map);
		
		map.tab_content.appendChild(service_container);
		
		if (this.getAttribution && map.attributionControl) {
			map.attributionControl.addAttribution(this.getAttribution());
		}

		this.fire('add');
		map.fire('layeradd', {layer: this});
	},

});

Service.include({
	// @method addAutocomplete(autocomplete: Autocomplete): this
	// Adds the given control to the map
	addAutocomplete: function (autocomplete) {
		var id = stamp(autocomplete);
		if (this._inputs[id]) { return this; }
		this._inputs[id] = autocomplete;
		autocomplete._mapToAdd = this;
		this.whenReady(autocomplete._imputAdd, autocomplete);
		return this;
	},
	// @method removeAutocomplete(autocomplete: Autocomplete): this
	// Removes the given control from the map
	removeAutocomplete: function (autocomplete) {
		autocomplete.remove();
		return this;
	}
});

var SimpleAutocomplete = Autocomplete.extend({
	// Classes extending `L.Layer` will inherit the following options:
	options: {
		autocomplete: true,
		placeholder: null,
		label:'Label'
	},
	initialize: function (options) {
		if (options && options.hasOwnProperty('onResponce')) {
			this.defaultOnResponce=options.onResponce;
		}
		setOptions(this, options);
	},
	onAdd: function (map) {
		var container = this._container = create$1('div', 'form-group');
		
		var label_ubigeo = create$1('label', ' ',container);
		//<span class="">
		label_ubigeo.innerHTML=this.options.label;
		var divclose = create$1('div', 'add-clear-span form-group has-feedback ',container);
		var input_ubigeo = this._input_ubigeo =  create$1('input', 'form-control',divclose);
		input_ubigeo.spellcheck = false;
		input_ubigeo.type="text";
		var id_input = stamp(input_ubigeo);
		input_ubigeo.id="input"+id_input;
		label_ubigeo.setAttribute('for','input'+id_input);
		
	    // Only set if title option is not null or falsy
	    if (this.options.textStrings['INPUT_TITLE_ATTRIBUTE']) {
	      input_ubigeo.title = this.options.textStrings['INPUT_TITLE_ATTRIBUTE'];
	    }

	    // Only set if placeholder option is not null or falsy
	    if (this.options.textStrings['INPUT_PLACEHOLDER']) {
	      input_ubigeo.placeholder = this.options.textStrings['INPUT_PLACEHOLDER'];
	    }
		 
		this._search = create$1('a', 'leaflet-pelias-search-icon', container);
		this._reset =  create$1('span', 'add-clear-x form-control-feedback geodir-icon-close geodir-hidden',divclose);
	    
	    this._results = create$1('div', 'leaflet-pelias-results leaflet-bar', divclose);
	    // Forwards focus and blur events from input to geocoder
	    DomEvent.addListener(this._input_ubigeo, 'focus', function (e) {
	      this.fire('focus', { originalEvent: e });
	    }, this);

	    DomEvent.addListener(this._input_ubigeo, 'blur', function (e) {
	      this.fire('blur', { originalEvent: e });
	    }, this);
	    DomEvent.on(this._reset, 'click', function (e) {
	        this.reset();
	        this._input_ubigeo.focus();
	        DomEvent.stopPropagation(e);
	      }, this);
	    DomEvent.on(this._input_ubigeo, 'keydown', function (e) {
	        var list = this._results.querySelectorAll('.leaflet-pelias-result');
	        var selected = this._results.querySelectorAll('.leaflet-pelias-selected')[0];
	        var selectedPosition;
	        var self = this;

	        var panToPoint = function (selected, options) {
	          if (selected && options.panToPoint) {
	            console.log(selected.feature);
	          }
	        };

	        var scrollSelectedResultIntoView = function (selected) {
	          var selectedRect = selected.getBoundingClientRect();
	          var resultsRect = self._results.getBoundingClientRect();
	          // Is the selected element not visible?
	          if (selectedRect.bottom > resultsRect.bottom) {
	            self._results.scrollTop = selected.offsetTop + selected.offsetHeight - self._results.offsetHeight;
	          } else if (selectedRect.top < resultsRect.top) {
	            self._results.scrollTop = selected.offsetTop;
	          }
	        };

	        for (var i = 0; i < list.length; i++) {
	          if (list[i] === selected) {
	            selectedPosition = i;
	            break;
	          }
	        }
	        // TODO cleanup
	        switch (e.keyCode) {
	          // 13 = enter
	          case 13:
	            if (selected) {
	              this.setSelectedResult(selected, e);
	            } else {
	              // perform a full text search on enter
	              var text = (e.target || e.srcElement).value;
	              this.search(text);
	            }
	            DomEvent.preventDefault(e);
	            break;
	          // 38 = up arrow
	          case 38:
	            // Ignore key if there are no results or if list is not visible
	            if (list.length === 0 || this._results.style.display === 'none') {
	              return;
	            }

	            if (selected) {
	              DomUtil.removeClass(selected, 'leaflet-pelias-selected');
	            }

	            var previousItem = list[selectedPosition - 1];
	            var highlighted = (selected && previousItem) ? previousItem : list[list.length - 1]; // eslint-disable-line no-redeclare

	            DomUtil.addClass(highlighted, 'leaflet-pelias-selected');
	            scrollSelectedResultIntoView(highlighted);
	            panToPoint(highlighted, this.options);
	            this._input_ubigeo.value = highlighted.textContent || highlighted.innerText;
	            DomEvent.preventDefault(e);
	            break;
	          // 40 = down arrow
	          case 40:
	            // Ignore key if there are no results or if list is not visible
	            if (list.length === 0 || this._results.style.display === 'none') {
	              return;
	            }

	            if (selected) {
	            	DomUtil.removeClass(selected, 'leaflet-pelias-selected');
	            }

	            var nextItem = list[selectedPosition + 1];
	            var highlighted = (selected && nextItem) ? nextItem : list[0]; // eslint-disable-line no-redeclare

	            DomUtil.addClass(highlighted, 'leaflet-pelias-selected');
	            scrollSelectedResultIntoView(highlighted);
	            panToPoint(highlighted, this.options);
	            this._input_ubigeo.value = highlighted.textContent || highlighted.innerText;
	            DomEvent.preventDefault(e);
	            break;
	          // all other keys
	          default:
	            break;
	        }
	      }, this);
	    
	      DomEvent.on(this._input_ubigeo, 'keyup', function (e) {
	    	  var key = e.which || e.keyCode;
	          var text = (e.target || e.srcElement).value;
	          if (text.length > 0) {
	            removeClass(this._reset, 'geodir-hidden');
	          } else {
	            addClass(this._reset, 'geodir-hidden');
	          }

	          // Ignore all further action if the keycode matches an arrow
	          // key (handled via keydown event)
	          if (key === 13 || key === 38 || key === 40) {
	            return;
	          }

	          // keyCode 27 = esc key (esc should clear results)
	          if (key === 27) {
	            // If input is blank or results have already been cleared
	            // (perhaps due to a previous 'esc') then pressing esc at
	            // this point will blur from input as well.
	            if (text.length === 0 || this._results.style.display === 'none') {
	              this._input.blur();

	              if (!this.options.expanded && L.DomUtil.hasClass(this._container, 'leaflet-pelias-expanded')) {
	                this.collapse();
	              }
	            }

	            // Clears results
	            this.clearResults(true);
	            DomUtil.removeClass(this._search, 'leaflet-pelias-loading');
	            return;
	          }

	          if (text !== this._lastValue) {
	            this._lastValue = text;

	            if (text.length >= MINIMUM_INPUT_LENGTH_FOR_AUTOCOMPLETE && this.options.autocomplete === true) {
	              this.autocomplete(text);
	            } else {
	              this.clearResults(true);
	            }
	          }
	        }, this);
	      DomEvent.on(this._results, 'click', function (e) {
	          DomEvent.preventDefault(e);
	          DomEvent.stopPropagation(e);

	          var _selected = this._results.querySelectorAll('.leaflet-pelias-selected')[0];
	          if (_selected) {
	            DomUtil.removeClass(_selected, 'leaflet-pelias-selected');
	          }
	          var selected = e.target || e.srcElement; /* IE8 */
	          var findParent = function () {
	            if (!DomUtil.hasClass(selected, 'leaflet-pelias-result')) {
	              selected = selected.parentElement;
	              if (selected) {
	                findParent();
	              }
	            }
	            return selected;
	          };

	          // click event can be registered on the child nodes
	          // that does not have the required coords prop
	          // so its important to find the parent.
	          findParent();

	          // If nothing is selected, (e.g. it's a message, not a result),
	          // do nothing.
	          if (selected) {
	            DomUtil.addClass(selected, 'leaflet-pelias-selected');
	            this.setSelectedResult(selected, e);
	          }
	        }, this);
		return container;
	},
	reset: function () {
		    this._input_ubigeo.value = '';
		    DomUtil.addClass(this._reset, 'geodir-hidden');
		    this.clearResults();
		    this.fire('reset');
	},
	autocomplete: throttle(function (input) {
	    // Prevent lack of input from sending a malformed query to Pelias
	    if (!input) return;
	    var url = this._map._map.options.serviceBase +this.options.resource;
	    var params = {
	    	search: input
	    };
	    this.callPelias(url, params, 'autocomplete');
	}, API_RATE_LIMIT),
	showMessage: function (text) {
		var resultsContainer = this._results;
		// Reset and display results container
		resultsContainer.innerHTML = '';
		resultsContainer.style.display = 'block';
		var messageEl = DomUtil.create('div', 'leaflet-pelias-message', resultsContainer);
		// Set text. This is the most cross-browser compatible method
		// and avoids the issues we have detecting either innerText vs textContent
		// (e.g. Firefox cannot detect textContent property on elements, but it's there)
		messageEl.appendChild(document.createTextNode(text));
	},
	onRemove: function (map) {
		if (this.dragging && this.dragging.enabled()) {
			this.options.draggable = true;
			this.dragging.removeHooks();
		}
		delete this.dragging;

		if (this._zoomAnimated) {
			map.off('zoomanim', this._animateZoom, this);
		}

		this._removeIcon();
		this._removeShadow();
	},
	getParams: function (params) {
		params = params || {};
		// Search API key
		if (this.apiKey) {
			params.api_key = this.apiKey;
		}
		var newParams = this.options.params;
		if (!newParams) {
			return params;
		}
		if (typeof newParams === 'object') {
			for (var prop in newParams) {
				params[prop] = newParams[prop];
			}
		}
		return params;
	},
	showResults: function (features, input) {
		// Exit function if there are no features
		if (features.length === 0) {
			this.showMessage(this.options.textStrings['NO_RESULTS']);
			return;
		}
		var resultsContainer = this._results;
		// Reset and display results container
		resultsContainer.innerHTML = '';
		resultsContainer.style.display = 'block';
		// manage result box height
		resultsContainer.style.maxHeight = '500px';
		var list = DomUtil.create('ul', 'leaflet-pelias-list', resultsContainer);
		for (var i = 0, j = features.length; i < j; i++) {
			var feature = features[i];
			var resultItem = DomUtil.create('li', 'leaflet-pelias-result', list);
			resultItem.feature = feature;
			resultItem.innerHTML += this.highlight(feature.name, input);
		}
	},
	getIconType: function (layer) {
		return {
	          type: 'class',
	          value: 'point'
	        };
	},
	highlight: function (text, focus) {
		var r = RegExp('(' + escapeRegExp(focus) + ')', 'gi');
		return text.replace(r, '<strong>$1</strong>');
	},
	setSelectedResult: function (selected, originalEvent) {
		this._input_ubigeo.value = selected.textContent || selected.innerText;
		this.blur();
		// Not all features will be guaranteed to have `gid` property - interpolated
		// addresses, for example, cannot be retrieved with `/place` and so the `gid`
		// property for them may be dropped in the future.
		if (this.options.place && selected.feature.id) {
			this.place(selected.feature.id);
		}
		this.defaultOnResponce(selected.feature);
	},
	blur: function () {
		this._input_ubigeo.blur();
		this.clearResults();
		if (this._input_ubigeo.value === '' && this._results.style.display !== 'none') {
			DomUtil.addClass(this._reset, 'leaflet-pelias-hidden');
			if (!this.options.expanded) {
				this.collapse();
			}
		}
	},
	collapse: function () {
		// 'expanded' options check happens outside of this function now
		// So it's now possible for a script to force-collapse a geocoder
		// that otherwise defaults to the always-expanded state
		DomUtil.removeClass(this._container, 'leaflet-pelias-expanded');
		this._input.blur();
		this.clearFullWidth();
		this.clearResults();
		this.fire('collapse');
	},
	clearResults: function (force) {
		// Hide results from view
		this._results.style.display = 'none';
		// Destroy contents if input has also cleared
		// OR if force is true
		if (this._input_ubigeo.value === '' || force === true) {
			this._results.innerHTML = '';
		}
		// Turn on scrollWheelZoom, if disabled. (`mouseout` does not fire on
		// the results list when it's closed in this way.)
		// this._enableMapScrollWheelZoom();
	},
	callPelias: function (endpoint, params, type) {
		let token = this._map._map.token;
		params = this.getParams(params);
		DomUtil.addClass(this._search, 'leaflet-pelias-loading');
	    // Track when the request began
		var reqStartedAt = new Date().getTime();
		function serialize (params) {
			var data = '';
			for (var key in params) {
		        if (params.hasOwnProperty(key)) {
		          var param = params[key];
		          var type = param.toString();
		          var value;

		          if (data.length) {
		            data += '&';
		          }

		          switch (type) {
		            case '[object Array]':
		              value = (param[0].toString() === '[object Object]') ? JSON.stringify(param) : param.join(',');
		              break;
		            case '[object Object]':
		              value = JSON.stringify(param);
		              break;
		            case '[object Date]':
		              value = param.valueOf();
		              break;
		            default:
		              value = param;
		              break;
		          }

		          data += encodeURIComponent(key) + '=' + encodeURIComponent(value);
		        }
		      }

		      return data;
		    }

		    var paramString = serialize(params);
		    var url = endpoint + '?' + paramString;
		    var self = this; // IE8 cannot .bind(this) without a polyfill.
		    function handleResponse (err, response) {
		      DomUtil.removeClass(self._search, 'leaflet-pelias-loading');
		      var results;

		      try {
		        results = JSON.parse(response.responseText);
		      } catch (e) {
		        err = {
		          code: 500,
		          message: 'Parse Error' // TODO: string
		        };
		      }

		      if (err) {
		        var errorMessage;
		        switch (err.code) {
		          // Error codes.
		          // https://mapzen.com/documentation/search/http-status-codes/
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
		          // Note the status code is 0 if CORS is not enabled on the error response
		          default:
		            errorMessage = self.options.textStrings['ERROR_DEFAULT'];
		            break;
		        }
		        self.showMessage(errorMessage);
		        self.fire('error', {
		          results: results,
		          endpoint: endpoint,
		          requestType: type,
		          params: params,
		          errorCode: err.code,
		          errorMessage: errorMessage
		        });
		      }

		      // There might be an error message from the geocoding service itself
		      if (results && results.geocoding && results.geocoding.errors) {
		        errorMessage = results.geocoding.errors[0];
		        self.showMessage(errorMessage);
		        self.fire('error', {
		          results: results,
		          endpoint: endpoint,
		          requestType: type,
		          params: params,
		          errorCode: err.code,
		          errorMessage: errorMessage
		        });
		        return;
		      }
		      // Autocomplete and search responses
		      if (results && results.results) {
		        // Check if request is stale:
		        // Only for autocomplete or search endpoints
		        // Ignore requests if input is currently blank
		        // Ignore requests that started before a request which has already
		        // been successfully rendered on to the UI.
		        if (type === 'autocomplete' || type === 'search') {
		          if (self._input_ubigeo.value === '' || self.maxReqTimestampRendered >= reqStartedAt) {
		            return;
		          } else {
		            // Record the timestamp of the request.
		            self.maxReqTimestampRendered = reqStartedAt;
		          }
		        }
		        // Placeholder: handle place response
		        if (type === 'place') {
		          self.handlePlaceResponse(results);
		        }
		        // Show results
		        if (type === 'autocomplete' || type === 'search') {
		        	
		          self.showResults(results.results, params.search);
		        }
		        // Fire event
		        self.fire('results', {
		          results: results,
		          endpoint: endpoint,
		          requestType: type,
		          params: params
		        });
		      }
		    }
		    corslite(url, handleResponse, true, token);
	},
});

var SimpleService = Service.extend({
	// @section
	// @aka Marker options
	options: {
		tabNav: new TabNavDefault(),
		// @option keyboard: Boolean = true
		// Whether the marker can be tabbed to with a keyboard and clicked by
		// pressing enter.
		keyboard: true,
		// @option title: String = ''
		// Text for the browser tooltip that appear on marker hover (no tooltip
		// by default).
		title: '',
		// @option opacity: Number = 1.0
		// The opacity of the marker.
		opacity: 1,
		// @option riseOnHover: Boolean = false
		// If `true`, the marker will get on top of others when you hover the
		// mouse over it.
		riseOnHover: false,
		// @option riseOffset: Number = 250
		// The z-index offset used for the `riseOnHover` feature.
		riseOffset: 250,
		// @option pane: String = 'markerPane'
		// `Map pane` where the markers icon will be added.
		pane: 'services',
		// @option bubblingMouseEvents: Boolean = false
		// When `true`, a mouse event on this marker will trigger the same event
		// on the map
		// (unless [`L.DomEvent.stopPropagation`](#domevent-stoppropagation) is
		// used).
		bubblingMouseEvents: false,
		expanded: true,

	},
	/*
	private int numero;
	private String manzana;
	private double[] location;
	private String device;
	 * */
	address:{},

	/*
	 * @section
	 * 
	 * In addition to [shared layer methods](#Layer) like `addTo()` and
	 * `remove()` and [popup methods](#Popup) like bindPopup() you can also use
	 * the following methods:
	 */
	initialize: function (options) {
		setOptions(this, options);
		this._inputs={};
		this._loaded = true;
	},	
	geocodeForward:function(data){
		var comp = this;
		let url= comp._map.options.serviceBase+ "/geodir.address/search";
		return new Promise(function (resolve, reject) {
			comp._geocodeForward(url,comp._map.token,data,resolve,reject);
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
		  xhttp.open("POST",url, true);
		  xhttp.setRequestHeader("Content-type", "application/json; charset=utf-8");
		  xhttp.setRequestHeader('Authorization', 'bearer '+token);
		  xhttp.send(_data);
	},
	onAdd: function (map) {
		var addres = this.address;
		var input_via = new SimpleAutocomplete({
			resource:'/geodir.address/120101/via',
			label:'Via',
			onResponce:function(result){
				addres.via=result.details.name;
				addres.tipo_via=result.details.n_subtipo;
				console.log(addres);
			}
		});
		
		
		var input_nucleo = new SimpleAutocomplete({
			resource:'/geodir.address/120101/nucleo',
			label:'Nucleo',
			onResponce:function(result){
				addres.nucleo=result.details.name;
				addres.tipo_nucleo=result.details.n_subtipo;
			}
		});
		var input_ubigeo = new SimpleAutocomplete({
			resource:'/geodir.address/distrito',
			label:'Distrito',
			onResponce:function(result){
				addres.ubigeo=result.ubigeo;
				input_via.changeResource('/geodir.address/'+result.ubigeo+'/via');
				input_nucleo.changeResource('/geodir.address/'+result.ubigeo+'/nucleo');
			}
		});
		
		input_ubigeo.addTo(this);
		input_via.addTo(this);
		
		var container_number = create$1('div', 'form-group',this.tab_content);
		var label_number = create$1('label', ' ',container_number);
		//<span class="">
		label_number.innerHTML='Numero';
		var divclose_number = create$1('div', 'add-clear-span form-group has-feedback ',container_number);
		var input_number = create$1('input', 'form-control',divclose_number);
		input_number.spellcheck = false;
		input_number.type="number";
		var id_input = stamp(input_number);
		input_number.id="input"+id_input;
		label_number.setAttribute('for','input'+id_input);
		input_number.placeholder="Numero";
		
		
		input_nucleo.addTo(this);
		
		var container_manzana = create$1('div', 'form-group',this.tab_content);
		var label_manzana = create$1('label', ' ',container_manzana);
		//<span class="">
		label_manzana.innerHTML='Manzana';
		var divclose_manzana = create$1('div', 'add-clear-span form-group has-feedback ',container_manzana);
		var input_manzana = create$1('input', 'form-control',divclose_manzana);
		input_manzana.spellcheck = false;
		input_manzana.type="text";
		var id_input = stamp(input_manzana);
		input_manzana.id="input"+id_input;
		label_manzana.setAttribute('for','input'+id_input);
		input_manzana.placeholder="Manzana";
		
		
		var btn_search = this._btn_search = create$1('button', 'btn btn-primary',this.tab_content);
		btn_search.innerHTML='Buscar';
		 DomEvent.on(this._btn_search, 'click', this.onSearch, this);
		var container = create$1('div', 'conatiner');
		this._initTabNav();
		return container;
	},
	onSearch:function(){
		var comp = this;
		let data = JSON.stringify(comp.address);
		console.log(data);
		let url= comp._map.options.serviceBase+ "/geodir.address/search";
		 function handleResponse (err, response) {
			 var results;
			 try {
				results = JSON.parse(response.responseText);
				console.log(results);
			 } catch (e) {
			        err = {
			          code: 500,
			          message: 'Parse Error' // TODO: string
			        };
			      console.log(e);
			 }
			 if (err) {
				 var errorMessage;
				 switch (err.code) {
			          // Error codes.
			          // https://mapzen.com/documentation/search/http-status-codes/
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
			          // Note the status code is 0 if CORS is not enabled on the error response
			          default:
			            errorMessage = self.options.textStrings['ERROR_DEFAULT'];
			            break;
			        }
				console.warn(errorMessage);
			}
			if(results&&results.features){
				console.log(results.features);
			}
		 }
		corslitePOST(url, handleResponse, true,comp._map.token,data);
	},
	onRemove: function (map) {
		if (this.dragging && this.dragging.enabled()) {
			this.options.draggable = true;
			this.dragging.removeHooks();
		}
		delete this.dragging;

		if (this._zoomAnimated) {
			map.off('zoomanim', this._animateZoom, this);
		}

		this._removeIcon();
		this._removeShadow();
	},
	_initTabNav: function () {
		var options = this.options;
		var tabNav = options.tabNav.createTabNav(this._tabNav);
		var count = Object.keys(this._map._layers).length;
		if(count==1){
			addClass(tabNav,"is-active");
		}
		this._tabNav = tabNav;
		this._tabNav.id = 'panel-geodir-'+this._geodir_id;
		this.getPane('services-header').appendChild(this._tabNav);
	},
});

// Instantiates a Marker object given a geographical point and optionally an
// options object.
function simpleService(options) {
	return new SimpleService(options);
}


var oldGeodir = window.Geodir;
function noConflict() {
	window.Geodir = oldGeodir;
	return this;
}
// Always export us to window global (see #2364)
window.Geodir = exports;
exports.Util=Util;
exports.TEXT_STRINGS=TEXT_STRINGS;
exports.Evented = Evented;
exports.version = version;
exports.noConflict = noConflict;
exports.Container = Container;
exports.container = createContainer;
exports.SimpleService = SimpleService;
exports.simpleService = simpleService;

})));
// # sourceMappingURL=geodir-src.js.map