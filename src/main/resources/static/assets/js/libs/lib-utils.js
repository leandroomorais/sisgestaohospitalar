"use strict";

function _typeof(obj) { "@babel/helpers - typeof"; if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

var ArrayUtil =
/** @class */
function () {
  function ArrayUtil() {}

  ArrayUtil.isContains = function (array, element) {
    if (array === undefined || array != null) {
      return false;
    }

    function getArray() {
      if (array) {
        if (!ArrayUtil.isArrayByType(array, 'object')) {
          for (var i = 0; i < array.length; i++) {
            var object = array[i];

            if (object === element) {
              return true;
            }
          }

          return false;
        } else if (ArrayUtil.isArrayByType(array, '')) {}
      }

      return false;
    }

    for (var i = 0; i < array.length; i++) {
      var object = array[i];
      var keys = Object.keys(object);

      if (keys.length > 0 && object !== element) {
        for (var j = 0; j < keys.length; j++) {
          if (object[keys[j]] === element) {
            return true;
          }
        }
      } else {
        if (object === element) {
          return true;
        }
      }
    }

    return false;
  };

  ArrayUtil.clear = function (array) {
    if (array.length > 0) {
      array = [];
    }

    return array;
  };

  ArrayUtil.isEmpty = function (array) {
    return array.length === 0;
  };

  ArrayUtil.pop = function (array, element) {
    var key = this.searchKeyByElement(array, element);
    array.splice(key, 1);
    return array;
  };

  ArrayUtil.isEmptyToArrays = function (array) {
    var len = 0;

    for (var i = 0; i < array.length; i++) {
      if (!this.isEmpty(array[i])) {
        len++;
      }
    }

    return len !== array.length;
  };

  ArrayUtil.searchKeyByElement = function (array, element) {
    if (array.length === 0) {
      return -1;
    }

    for (var i = 0; i < array.length; i++) {
      var object = array[i];
      var keys = Object.keys(object);

      if (keys.length > 0 && object !== element) {
        for (var j = 0; j < keys.length; j++) {
          if (object[keys[j]] === element) {
            return i;
          }
        }
      } else {
        if (object === element) {
          return i;
        }
      }
    }

    return -1;
  };

  ArrayUtil.push = function (array, element) {
    if (!this.isContains(array, element)) {
      array.push(element);
    }
  };

  ArrayUtil.isArray = function (array) {
    if (Array.isArray(array)) {
      if (!this.isEmpty(array)) {
        if (this.isArrayByType(array, 'object')) {
          return true;
        }
      }
    } else if (this.isArrayByType(array, 'object')) {
      if (!this.isEmpty(array)) {
        return true;
      }
    }

    return false;
  };

  ArrayUtil.isArrayOf = function (array) {
    if (Array.isArray(array)) {
      if (!this.isEmpty(array)) {
        return true;
      }
    }

    return false;
  };

  ArrayUtil.isArrayByType = function (array, type) {
    var count = 0;

    if (!(array === null || array === void 0 ? void 0 : array.length)) {
      return false;
    }

    for (var _i = 0, array_1 = array; _i < array_1.length; _i++) {
      var element = array_1[_i];

      if (_typeof(element) === type) {
        count++;
      }
    }

    if (count > 0) {
      if (array.length === count) {
        return true;
      }
    }

    return false;
  };

  return ArrayUtil;
}();
"use strict";

var ValidUtil =
/** @class */
function () {
  function ValidUtil() {}

  ValidUtil.isFieldValid = function (value, min, max) {
    if (this.isFieldNotEmpty(value)) {
      if (this.isFieldMinValid(value, min) && this.isFieldMaxValid(value, max)) {
        return true;
      }
    }

    return false;
  };

  ValidUtil.isValidNumber = function (event) {
    return event.key === '0' || event.key === '1' || event.key === '2' || event.key === '3' || event.key === '4' || event.key === '5' || event.key === '6' || event.key === '7' || event.key === '8' || event.key === '9' || event.key === 'Backspace' || event.key === "Delete";
  };

  ValidUtil.isFieldNotEmpty = function (value) {
    return value !== "";
  };

  ValidUtil.isFieldMinValid = function (value, min) {
    return value.length >= min;
  };

  ValidUtil.isFieldMaxValid = function (value, max) {
    return value.length <= max - 1;
  };

  ValidUtil.isNumberRepetion = function (value) {
    var number0 = value.replace(/[0^0]/g, '');
    var number1 = value.replace(/[1^1]/g, '');
    var number2 = value.replace(/[2^2]/g, '');
    var number3 = value.replace(/[3^3]/g, '');
    var number4 = value.replace(/[4^4]/g, '');
    var number5 = value.replace(/[5^5]/g, '');
    var number6 = value.replace(/[6^6]/g, '');
    var number7 = value.replace(/[7^7]/g, '');
    var number8 = value.replace(/[8^8]/g, '');
    var number9 = value.replace(/[9^9]/g, '');
    return !(number0.trim() === "" || number1.trim() === "" || number2.trim() === "" || number3.trim() === "" || number4.trim() === "" || number5.trim() === "" || number6.trim() === "" || number7.trim() === "" || number8.trim() === "" || number9.trim() === "");
  };

  ValidUtil.number = function (value) {
    return value.replace(/[^0-9]/g, '');
  };
  /*funcao util que separa o valor de acordo com os argumentos no paramentro*/

  /*funcao recursiva*/


  ValidUtil.separator = function (value, args, pos, strs) {
    var arg = args[pos];
    pos++;

    if (args) {
      var vals = value.split(arg);

      for (var i = 0; i < vals.length; i++) {
        var val = vals[i];

        if (this.search(val, args)) {
          this.separator(val, args, pos, strs);
        } else {
          if (val.trim() !== '') {
            strs.push(val);
          }
        }
      }
    }
  };
  /*funcao util que faz a busca se o val possui os args*/


  ValidUtil.search = function (val, args) {
    for (var i = 0; i < args.length; i++) {
      var pos = val.search(args[i]);

      if (pos > -1) {
        if (pos === 0) {
          if (val[0] === args[i]) {
            return true;
          }

          continue;
        }

        return true;
      }
    }

    return false;
  };
  /*funcao util que verifica se existe a chave no objeto*/


  ValidUtil.isExistKey = function (object, key) {
    var array = object !== undefined ? Object.keys(object) : [];

    for (var i = 0; i < array.length; i++) {
      if (array[i] === key) {
        return true;
      }
    }

    return false;
  };

  ValidUtil.getElementsByChecked = function (elements) {
    if (ArrayUtil.isArray(elements)) {
      var newElements = [];

      for (var _i = 0, elements_1 = elements; _i < elements_1.length; _i++) {
        var element = elements_1[_i];

        if (element === null || element === void 0 ? void 0 : element.checked) {
          newElements.push(element);
        }
      }

      return newElements;
    }

    return [];
  };

  ValidUtil.getElementByChecked = function (elements) {
    if (ArrayUtil.isArray(elements)) {
      for (var _i = 0, elements_2 = elements; _i < elements_2.length; _i++) {
        var element = elements_2[_i];

        if (element === null || element === void 0 ? void 0 : element.checked) {
          return element;
        }
      }
    }

    return null;
  };
  /*funcao util que verifica se os inputs (checkbox) estão selecionados*/


  ValidUtil.isValidChecked = function (elements) {
    var _a;

    if (ArrayUtil.isArray(elements)) {
      var increment = 0;

      for (var i = 0; i < elements.length; i++) {
        if (!((_a = elements[i]) === null || _a === void 0 ? void 0 : _a.checked)) {
          increment++;
        }
      }

      return elements.length !== increment;
    }

    return false;
  };
  /*funcao util que verifica se os inputs (checkbox) estão selecionados*/


  ValidUtil.isValidRadio = function (elements) {
    if (ArrayUtil.isArray(elements)) {
      var increment = 0;

      for (var i = 0; i < elements.length; i++) {
        if (elements[i].checked) {
          increment++;
        }
      }

      return increment > 0;
    }

    return false;
  };
  /*funcao util que verifica se o elemento possui o atributo*/


  ValidUtil.isAttr = function (attr, array, element) {
    if (ArrayUtil.isArrayOf(array)) {
      for (var i = 0; i < array.length; i++) {
        if (element[attr] === array[i]) {
          return true;
        }
      }
    }

    return false;
  };
  /*funcao util que verifica se a string é valida*/


  ValidUtil.isValidString = function (value) {
    if (value.replace) {
      if (value.search('null') >= 0) {
        return false;
      } else if (value.search('undefined') >= 0) {
        return false;
      }

      var bol = value.replace(/[0-9^a-z]/g, '');
      return bol !== '';
    }

    return false;
  };

  ValidUtil.isEmpty = function (value) {
    return value.trim() === '';
  };

  return ValidUtil;
}();
"use strict";

var URIUtil =
/** @class */
function () {
  function URIUtil() {}

  URIUtil.get = function (_a) {
    var uri = _a.uri,
        params = _a.params;

    function checkToArray(uri, array) {
      if (uri[uri.length - 1] === '/') {
        return uri + array.join('/');
      }

      return uri + '/' + array.join('/');
    }

    function checkURIParam(uri, array) {
      if (uri[uri.length - 1] === '/') {
        return uri + '?' + array.join('&');
      }

      return uri + '?' + array.join('&');
    }

    if (params) {
      if (Array.isArray(params)) {
        var array = [];

        for (var _i = 0, params_1 = params; _i < params_1.length; _i++) {
          var value = params_1[_i];
          array.push(value);
        }

        return checkToArray(uri, array);
      } else {
        if (Object.keys(params).length > 0) {
          var array = [];
          var keys = Object.keys(params);

          for (var _b = 0, keys_1 = keys; _b < keys_1.length; _b++) {
            var key = keys_1[_b]; //@ts-ignore

            array.push(key + "=" + params[key]);
          }

          return checkURIParam(uri, array);
        }
      }
    }

    return uri;
  };

  return URIUtil;
}();
"use strict";

var __awaiter = this && this.__awaiter || function (thisArg, _arguments, P, generator) {
  function adopt(value) {
    return value instanceof P ? value : new P(function (resolve) {
      resolve(value);
    });
  }

  return new (P || (P = Promise))(function (resolve, reject) {
    function fulfilled(value) {
      try {
        step(generator.next(value));
      } catch (e) {
        reject(e);
      }
    }

    function rejected(value) {
      try {
        step(generator["throw"](value));
      } catch (e) {
        reject(e);
      }
    }

    function step(result) {
      result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected);
    }

    step((generator = generator.apply(thisArg, _arguments || [])).next());
  });
};

var __generator = this && this.__generator || function (thisArg, body) {
  var _ = {
    label: 0,
    sent: function sent() {
      if (t[0] & 1) throw t[1];
      return t[1];
    },
    trys: [],
    ops: []
  },
      f,
      y,
      t,
      g;
  return g = {
    next: verb(0),
    "throw": verb(1),
    "return": verb(2)
  }, typeof Symbol === "function" && (g[Symbol.iterator] = function () {
    return this;
  }), g;

  function verb(n) {
    return function (v) {
      return step([n, v]);
    };
  }

  function step(op) {
    if (f) throw new TypeError("Generator is already executing.");

    while (_) {
      try {
        if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
        if (y = 0, t) op = [op[0] & 2, t.value];

        switch (op[0]) {
          case 0:
          case 1:
            t = op;
            break;

          case 4:
            _.label++;
            return {
              value: op[1],
              done: false
            };

          case 5:
            _.label++;
            y = op[1];
            op = [0];
            continue;

          case 7:
            op = _.ops.pop();

            _.trys.pop();

            continue;

          default:
            if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) {
              _ = 0;
              continue;
            }

            if (op[0] === 3 && (!t || op[1] > t[0] && op[1] < t[3])) {
              _.label = op[1];
              break;
            }

            if (op[0] === 6 && _.label < t[1]) {
              _.label = t[1];
              t = op;
              break;
            }

            if (t && _.label < t[2]) {
              _.label = t[2];

              _.ops.push(op);

              break;
            }

            if (t[2]) _.ops.pop();

            _.trys.pop();

            continue;
        }

        op = body.call(thisArg, _);
      } catch (e) {
        op = [6, e];
        y = 0;
      } finally {
        f = t = 0;
      }
    }

    if (op[0] & 5) throw op[1];
    return {
      value: op[0] ? op[1] : void 0,
      done: true
    };
  }
};

var Res =
/** @class */
function () {
  function Res() {}

  Res.get = function (_a) {
    var res = _a.res,
        onSuccess = _a.onSuccess,
        onError = _a.onError;
    return __awaiter(void 0, void 0, void 0, function () {
      return __generator(this, function (_b) {
        return [2
        /*return*/
        , new Promise(function (resolve) {
          return __awaiter(void 0, void 0, void 0, function () {
            var resp;
            return __generator(this, function (_a) {
              switch (_a.label) {
                case 0:
                  return [4
                  /*yield*/
                  , Res.getData(res)];

                case 1:
                  resp = _a.sent();

                  if (resp.status >= 200 && resp.status <= 299) {
                    resolve(onSuccess(resp));
                  } else {
                    resolve(onError(resp));
                  }

                  return [2
                  /*return*/
                  ];
              }
            });
          });
        })];
      });
    });
  };

  Res.getData = function (res) {
    return __awaiter(void 0, void 0, void 0, function () {
      var json, text, e_1;
      return __generator(this, function (_a) {
        switch (_a.label) {
          case 0:
            _a.trys.push([0, 5,, 6]);

            return [4
            /*yield*/
            , res.json()];

          case 1:
            json = _a.sent();
            if (!json) return [3
            /*break*/
            , 2];
            return [2
            /*return*/
            , {
              status: res.status,
              data: json
            }];

          case 2:
            return [4
            /*yield*/
            , res.text()];

          case 3:
            text = _a.sent();

            if (text) {
              return [2
              /*return*/
              , {
                status: res.status,
                data: text
              }];
            }

            return [2
            /*return*/
            , {
              status: res.status,
              data: null
            }];

          case 4:
            return [3
            /*break*/
            , 6];

          case 5:
            e_1 = _a.sent();
            return [3
            /*break*/
            , 6];

          case 6:
            return [2
            /*return*/
            , {
              status: res.status,
              data: null
            }];
        }
      });
    });
  };

  return Res;
}();
"use strict";

var __assign = this && this.__assign || function () {
  __assign = Object.assign || function (t) {
    for (var s, i = 1, n = arguments.length; i < n; i++) {
      s = arguments[i];

      for (var p in s) {
        if (Object.prototype.hasOwnProperty.call(s, p)) t[p] = s[p];
      }
    }

    return t;
  };

  return __assign.apply(this, arguments);
};

var __awaiter = this && this.__awaiter || function (thisArg, _arguments, P, generator) {
  function adopt(value) {
    return value instanceof P ? value : new P(function (resolve) {
      resolve(value);
    });
  }

  return new (P || (P = Promise))(function (resolve, reject) {
    function fulfilled(value) {
      try {
        step(generator.next(value));
      } catch (e) {
        reject(e);
      }
    }

    function rejected(value) {
      try {
        step(generator["throw"](value));
      } catch (e) {
        reject(e);
      }
    }

    function step(result) {
      result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected);
    }

    step((generator = generator.apply(thisArg, _arguments || [])).next());
  });
};

var __generator = this && this.__generator || function (thisArg, body) {
  var _ = {
    label: 0,
    sent: function sent() {
      if (t[0] & 1) throw t[1];
      return t[1];
    },
    trys: [],
    ops: []
  },
      f,
      y,
      t,
      g;
  return g = {
    next: verb(0),
    "throw": verb(1),
    "return": verb(2)
  }, typeof Symbol === "function" && (g[Symbol.iterator] = function () {
    return this;
  }), g;

  function verb(n) {
    return function (v) {
      return step([n, v]);
    };
  }

  function step(op) {
    if (f) throw new TypeError("Generator is already executing.");

    while (_) {
      try {
        if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
        if (y = 0, t) op = [op[0] & 2, t.value];

        switch (op[0]) {
          case 0:
          case 1:
            t = op;
            break;

          case 4:
            _.label++;
            return {
              value: op[1],
              done: false
            };

          case 5:
            _.label++;
            y = op[1];
            op = [0];
            continue;

          case 7:
            op = _.ops.pop();

            _.trys.pop();

            continue;

          default:
            if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) {
              _ = 0;
              continue;
            }

            if (op[0] === 3 && (!t || op[1] > t[0] && op[1] < t[3])) {
              _.label = op[1];
              break;
            }

            if (op[0] === 6 && _.label < t[1]) {
              _.label = t[1];
              t = op;
              break;
            }

            if (t && _.label < t[2]) {
              _.label = t[2];

              _.ops.push(op);

              break;
            }

            if (t[2]) _.ops.pop();

            _.trys.pop();

            continue;
        }

        op = body.call(thisArg, _);
      } catch (e) {
        op = [6, e];
        y = 0;
      } finally {
        f = t = 0;
      }
    }

    if (op[0] & 5) throw op[1];
    return {
      value: op[0] ? op[1] : void 0,
      done: true
    };
  }
};

var __rest = this && this.__rest || function (s, e) {
  var t = {};

  for (var p in s) {
    if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0) t[p] = s[p];
  }

  if (s != null && typeof Object.getOwnPropertySymbols === "function") for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
    if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i])) t[p[i]] = s[p[i]];
  }
  return t;
};

var Req =
/** @class */
function () {
  function Req() {}

  Req.getJSON = function (_a) {
    var uri = _a.uri,
        params = _a.params,
        headers = _a.headers,
        onSuccess = _a.onSuccess,
        onError = _a.onError,
        rest = __rest(_a, ["uri", "params", "headers", "onSuccess", "onError"]);

    return __awaiter(this, void 0, void 0, function () {
      var _this = this;

      return __generator(this, function (_b) {
        switch (_b.label) {
          case 0:
            if (!(onSuccess === undefined || onError === undefined)) return [3
            /*break*/
            , 3];
            if (!(uri !== undefined)) return [3
            /*break*/
            , 2];
            return [4
            /*yield*/
            , this.onGetFetchData({
              uri: URIUtil.get({
                uri: uri,
                params: params
              }),
              data: {
                method: 'GET',
                headers: __assign({
                  "Accept": "application/json",
                  "Content-Type": "application/json"
                }, headers),
                rest: rest
              }
            })];

          case 1:
            return [2
            /*return*/
            , _b.sent()];

          case 2:
            return [3
            /*break*/
            , 4];

          case 3:
            if (onSuccess !== undefined && onError !== undefined && uri !== undefined) {
              new Promise(function (resolve) {
                return __awaiter(_this, void 0, void 0, function () {
                  var _a, _b, _c;

                  var _d;

                  return __generator(this, function (_e) {
                    switch (_e.label) {
                      case 0:
                        _a = resolve;
                        _c = (_b = Res).get;
                        _d = {};
                        return [4
                        /*yield*/
                        , this.onGetFetchData({
                          uri: URIUtil.get({
                            uri: uri,
                            params: params
                          }),
                          data: {
                            method: 'GET',
                            headers: __assign({
                              "Accept": "application/json",
                              "Content-Type": "application/json"
                            }, headers),
                            rest: rest
                          }
                        })];

                      case 1:
                        return [4
                        /*yield*/
                        , _c.apply(_b, [(_d.res = _e.sent(), _d.onSuccess = onSuccess, _d.onError = onError, _d)])];

                      case 2:
                        _a.apply(void 0, [_e.sent()]);

                        return [2
                        /*return*/
                        ];
                    }
                  });
                });
              });
            }

            _b.label = 4;

          case 4:
            return [2
            /*return*/
            ];
        }
      });
    });
  };

  Req.postJSON = function (_a) {
    var uri = _a.uri,
        body = _a.body,
        headers = _a.headers,
        onSuccess = _a.onSuccess,
        onError = _a.onError,
        rest = __rest(_a, ["uri", "body", "headers", "onSuccess", "onError"]);

    return __awaiter(this, void 0, void 0, function () {
      var _this = this;

      return __generator(this, function (_b) {
        if (uri !== undefined && body !== undefined && onSuccess !== undefined && onError !== undefined) {
          new Promise(function (resolve) {
            return __awaiter(_this, void 0, void 0, function () {
              var _a, _b, _c;

              var _d;

              return __generator(this, function (_e) {
                switch (_e.label) {
                  case 0:
                    _a = resolve;
                    _c = (_b = Res).get;
                    _d = {};
                    return [4
                    /*yield*/
                    , this.onGetFetchData({
                      uri: URIUtil.get({
                        uri: uri
                      }),
                      data: {
                        method: "POST",
                        body: JSON.stringify(body),
                        headers: __assign({
                          "Accept": "application/json",
                          "Content-Type": "application/json"
                        }, headers),
                        rest: rest
                      }
                    })];

                  case 1:
                    return [4
                    /*yield*/
                    , _c.apply(_b, [(_d.res = _e.sent(), _d.onSuccess = onSuccess, _d.onError = onError, _d)])];

                  case 2:
                    _a.apply(void 0, [_e.sent()]);

                    return [2
                    /*return*/
                    ];
                }
              });
            });
          });
        } else if (uri !== undefined && body !== undefined) {
          return [2
          /*return*/
          , new Promise(function (resolve) {
            return __awaiter(_this, void 0, void 0, function () {
              var _a;

              return __generator(this, function (_b) {
                switch (_b.label) {
                  case 0:
                    _a = resolve;
                    return [4
                    /*yield*/
                    , this.onGetFetchData({
                      uri: URIUtil.get({
                        uri: uri
                      }),
                      data: {
                        method: "POST",
                        body: JSON.stringify(body),
                        headers: __assign({
                          "Accept": "application/json",
                          "Content-Type": "application/json"
                        }, headers)
                      }
                    })];

                  case 1:
                    _a.apply(void 0, [_b.sent()]);

                    return [2
                    /*return*/
                    ];
                }
              });
            });
          })];
        }

        return [2
        /*return*/
        ];
      });
    });
  };

  Req.putJSON = function (_a) {
    var uri = _a.uri,
        params = _a.params,
        body = _a.body,
        headers = _a.headers,
        onSuccess = _a.onSuccess,
        onError = _a.onError,
        rest = __rest(_a, ["uri", "params", "body", "headers", "onSuccess", "onError"]);

    return __awaiter(this, void 0, void 0, function () {
      var _this = this;

      return __generator(this, function (_b) {
        if (uri !== undefined && body !== undefined && onSuccess && onError) {
          new Promise(function (resolve) {
            return __awaiter(_this, void 0, void 0, function () {
              var _a, _b, _c;

              var _d;

              return __generator(this, function (_e) {
                switch (_e.label) {
                  case 0:
                    _a = resolve;
                    _c = (_b = Res).get;
                    _d = {};
                    return [4
                    /*yield*/
                    , this.onGetFetchData({
                      uri: URIUtil.get({
                        uri: uri,
                        params: params
                      }),
                      data: {
                        method: "PUT",
                        body: JSON.stringify(body),
                        headers: __assign({
                          "Accept": "application/json",
                          "Content-Type": "application/json"
                        }, headers),
                        rest: rest
                      }
                    })];

                  case 1:
                    return [4
                    /*yield*/
                    , _c.apply(_b, [(_d.res = _e.sent(), _d.onSuccess = onSuccess, _d.onError = onError, _d)])];

                  case 2:
                    _a.apply(void 0, [_e.sent()]);

                    return [2
                    /*return*/
                    ];
                }
              });
            });
          });
        } else if (uri !== undefined && body !== undefined) {
          return [2
          /*return*/
          , new Promise(function (resolve) {
            return __awaiter(_this, void 0, void 0, function () {
              var _a;

              return __generator(this, function (_b) {
                switch (_b.label) {
                  case 0:
                    _a = resolve;
                    return [4
                    /*yield*/
                    , this.onGetFetchData({
                      uri: URIUtil.get({
                        uri: uri,
                        params: params
                      }),
                      data: {
                        method: "PUT",
                        body: JSON.stringify(body),
                        headers: __assign({
                          "Accept": "application/json",
                          "Content-Type": "application/json"
                        }, headers),
                        rest: rest
                      }
                    })];

                  case 1:
                    _a.apply(void 0, [_b.sent()]);

                    return [2
                    /*return*/
                    ];
                }
              });
            });
          })];
        }

        return [2
        /*return*/
        ];
      });
    });
  };

  Req.deleteJSON = function (_a) {
    var uri = _a.uri,
        params = _a.params,
        headers = _a.headers,
        onSuccess = _a.onSuccess,
        onError = _a.onError,
        rest = __rest(_a, ["uri", "params", "headers", "onSuccess", "onError"]);

    return __awaiter(this, void 0, void 0, function () {
      var _this = this;

      return __generator(this, function (_b) {
        if (uri !== undefined && params !== undefined && onSuccess !== undefined && onError !== undefined) {
          new Promise(function (resolve) {
            return __awaiter(_this, void 0, void 0, function () {
              var _a, _b, _c;

              var _d;

              return __generator(this, function (_e) {
                switch (_e.label) {
                  case 0:
                    _a = resolve;
                    _c = (_b = Res).get;
                    _d = {};
                    return [4
                    /*yield*/
                    , this.onGetFetchData({
                      uri: URIUtil.get({
                        uri: uri,
                        params: params
                      }),
                      data: {
                        method: "delete",
                        headers: headers,
                        rest: rest
                      }
                    })];

                  case 1:
                    return [4
                    /*yield*/
                    , _c.apply(_b, [(_d.res = _e.sent(), _d.onSuccess = onSuccess, _d.onError = onError, _d)])];

                  case 2:
                    _a.apply(void 0, [_e.sent()]);

                    return [2
                    /*return*/
                    ];
                }
              });
            });
          });
        } else if (uri !== undefined && params !== undefined) {
          return [2
          /*return*/
          , new Promise(function (resolve) {
            return __awaiter(_this, void 0, void 0, function () {
              var _a;

              return __generator(this, function (_b) {
                switch (_b.label) {
                  case 0:
                    _a = resolve;
                    return [4
                    /*yield*/
                    , this.onGetFetchData({
                      uri: URIUtil.get({
                        uri: uri,
                        params: params
                      }),
                      data: {
                        method: "delete",
                        headers: headers,
                        rest: rest
                      }
                    })];

                  case 1:
                    _a.apply(void 0, [_b.sent()]);

                    return [2
                    /*return*/
                    ];
                }
              });
            });
          })];
        }

        return [2
        /*return*/
        ];
      });
    });
  };

  Req.request = function (_a) {
    var uri = _a.uri,
        onSuccess = _a.onSuccess,
        onError = _a.onError,
        method = _a.method,
        params = _a.params,
        body = _a.body,
        headers = _a.headers,
        mode = _a.mode,
        cache = _a.cache,
        credentials = _a.credentials;
    return __awaiter(void 0, void 0, void 0, function () {
      var init, filter, resp;
      return __generator(this, function (_b) {
        init = {
          method: method,
          body: JSON.stringify(body),
          headers: headers,
          mode: mode,
          cache: cache,
          credentials: credentials
        };

        filter = function filter(value) {
          var keys = Object.keys(value);

          for (var i = 0; i < keys.length; i++) {
            if (value[keys[i]]) {
              if (typeof value[keys[i]] === "string") {
                if (!ValidUtil.isValidString(value[keys[i]])) {
                  delete value[keys[i]];
                }
              } else {
                if (Object.keys(value[keys[i]]).length !== 0) {
                  delete value[keys[i]];
                }
              }
            } else {
              delete value[keys[i]];
            }
          }

          return value;
        };

        filter(init);

        try {
          resp = new Promise(function (resolve) {
            return __awaiter(void 0, void 0, void 0, function () {
              var resp, _a, _b;

              var _c;

              return __generator(this, function (_d) {
                switch (_d.label) {
                  case 0:
                    _b = (_a = Res).get;
                    _c = {};
                    return [4
                    /*yield*/
                    , fetch(URIUtil.get({
                      uri: uri,
                      params: params
                    }), filter(init))];

                  case 1:
                    return [4
                    /*yield*/
                    , _b.apply(_a, [(_c.res = _d.sent(), _c.onSuccess = onSuccess, _c.onError = onError, _c)])];

                  case 2:
                    resp = _d.sent();
                    resolve(resp);
                    return [2
                    /*return*/
                    ];
                }
              });
            });
          });
          return [2
          /*return*/
          , resp];
        } catch (e) {}

        return [2
        /*return*/
        , new Promise(function (resolve) {
          resolve(onError({
            status: 500,
            data: null
          }));
        })];
      });
    });
  };

  Req.onGetFetchData = function (_a) {
    var uri = _a.uri,
        data = _a.data;
    return __awaiter(void 0, void 0, void 0, function () {
      var res;
      return __generator(this, function (_b) {
        switch (_b.label) {
          case 0:
            res = fetch(uri, data);
            return [4
            /*yield*/
            , res];

          case 1:
            return [2
            /*return*/
            , _b.sent()];
        }
      });
    });
  };

  Req.onGetFetch = function (_a) {
    var uri = _a.uri;
    return __awaiter(void 0, void 0, void 0, function () {
      var res;
      return __generator(this, function (_b) {
        switch (_b.label) {
          case 0:
            res = fetch(uri);
            return [4
            /*yield*/
            , res];

          case 1:
            return [2
            /*return*/
            , _b.sent()];
        }
      });
    });
  };

  return Req;
}();
