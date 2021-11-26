"use strict";

var ElementsUtils =
/** @class */
function () {
  function ElementsUtils() {}

  ElementsUtils.setInnerHTML = function (_a, document) {
    var innerHTML = _a.innerHTML,
        parentElement = _a.parentElement;

    if (parentElement) {
      var id = parentElement.id,
          className = parentElement.className;

      if (id) {
        var element = document === null || document === void 0 ? void 0 : document.getElementById(id);

        if (element) {
          element.innerHTML += innerHTML;
        }
      } else if (className) {
        var element = document === null || document === void 0 ? void 0 : document.getElementsByClassName(className)[0];

        if (element) {
          element.innerHTML += innerHTML;
        }
      }
    }
  };

  return ElementsUtils;
}();
"use strict";

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

var TemplateDefault =
/** @class */
function () {
  function TemplateDefault(doc) {
    var _this = this;

    var _a;

    this._document = null;
    this._doc = null;

    this.getBody = function (_a) {
      var _b;

      var rest = __rest(_a, []);

      Attrs.set((_b = _this._document) === null || _b === void 0 ? void 0 : _b.body, rest);
    };

    this.getHtml = function (_a) {
      var _b;

      var title = _a.title,
          links = _a.links; //@ts-ignore

      (_b = _this._document) === null || _b === void 0 ? void 0 : _b.head.innerHTML = "<meta charset=\"UTF-8\">\n        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n        <title>" + title + "</title>\n        <style>\n            " + ExtractPDF.extractMap(links, function (t, index) {
        return "@import '" + t.href + "';";
      }) + "\n        </style>";
    };

    this.createHTML = function (_a) {
      var _b;

      var innerHTML = _a.innerHTML,
          parentElement = _a.parentElement;

      if (typeof innerHTML !== 'undefined') {
        if (!parentElement) {
          //@ts-ignore
          (_b = _this.document) === null || _b === void 0 ? void 0 : _b.body.innerHTML += innerHTML;
        } else {
          ElementsUtils.setInnerHTML({
            innerHTML: innerHTML,
            parentElement: parentElement
          }, _this._document);
        }
      }
    };

    this.createElements = function (_a) {
      var values = _a.values,
          onRender = _a.onRender,
          parentElement = _a.parentElement;

      if (onRender) {
        values === null || values === void 0 ? void 0 : values.map(function (value, index) {
          return _this.createHTML({
            innerHTML: onRender(value, index),
            parentElement: parentElement
          });
        });
      }
    };

    this.createStyle = function (_a) {
      var _b, _c;

      var value = _a.value;
      var style = value.trim() !== '' ? "<style> " + value + " </style>" : "<style>\n        .row {\n            display: flex;\n            align-items: center;\n            flex-direction: row;\n            justify-content: space-between;\n        }\n\n        .left {\n            display: flex;\n            justify-content: flex-start;\n            max-width: 50%;\n        }\n\n        .right {\n            display: flex;\n            justify-content: flex-end;\n            max-width: 50%;\n        }\n\n        .center {\n            display: flex;\n            flex-wrap: nowrap;\n            flex-direction: row;\n            align-content: center;\n            justify-content: center;\n            align-items: center;\n            text-align: center;\n        }\n\n        .container {\n            display: flex;\n            flex-direction: column;\n            flex-wrap: wrap;\n            align-content: stretch;\n            justify-content: center;\n            align-items: stretch;\n        }\n    </style>"; //@ts-ignore

      (_c = (_b = _this._document) === null || _b === void 0 ? void 0 : _b.head) === null || _c === void 0 ? void 0 : _c.innerHTML += style;
    };

    this.createScript = function (_a) {
      var _b;

      var src = _a.src,
          type = _a.type,
          rest = __rest(_a, ["src", "type"]); //@ts-ignore


      var script = new Tag({
        tagName: 'script',
        attrs: {
          src: src,
          type: type
        }
      }); //@ts-ignore

      View.append(new TagView(script).element, (_b = _this.document) === null || _b === void 0 ? void 0 : _b.body);
    };

    this.createLink = function (_a) {
      var _b;

      var href = _a.href,
          rel = _a.rel,
          type = _a.type,
          rest = __rest(_a, ["href", "rel", "type"]); //@ts-ignore


      var link = new Tag({
        tagName: 'link',
        attrs: {
          href: href,
          rel: rel,
          type: type
        }
      }); //@ts-ignore

      View.append(new TagView(link).element, (_b = _this.document) === null || _b === void 0 ? void 0 : _b.head);
    };

    this._document = (_a = doc.get()) === null || _a === void 0 ? void 0 : _a.document;
    this._doc = doc;
  }

  Object.defineProperty(TemplateDefault.prototype, "document", {
    get: function get() {
      return this._document;
    },
    enumerable: false,
    configurable: true
  });
  return TemplateDefault;
}();
"use strict";

var __extends = this && this.__extends || function () {
  var _extendStatics = function extendStatics(d, b) {
    _extendStatics = Object.setPrototypeOf || {
      __proto__: []
    } instanceof Array && function (d, b) {
      d.__proto__ = b;
    } || function (d, b) {
      for (var p in b) {
        if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p];
      }
    };

    return _extendStatics(d, b);
  };

  return function (d, b) {
    if (typeof b !== "function" && b !== null) throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");

    _extendStatics(d, b);

    function __() {
      this.constructor = d;
    }

    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
  };
}();

var TemplateDoc =
/** @class */
function (_super) {
  __extends(TemplateDoc, _super);

  function TemplateDoc(doc) {
    return _super.call(this, doc) || this;
  }

  return TemplateDoc;
}(TemplateDefault);
"use strict";

var Doc =
/** @class */
function () {
  function Doc(_a) {
    var url = _a.url,
        target = _a.target,
        features = _a.features;
    this._window = window.open(url, target, features);
  }

  Doc.prototype.print = function () {
    var _a, _b, _c;

    if (!((_a = this.get()) === null || _a === void 0 ? void 0 : _a.closed)) {
      (_b = this.getDoc()) === null || _b === void 0 ? void 0 : _b.close();
      (_c = this.get()) === null || _c === void 0 ? void 0 : _c.print();
    }
  };

  Doc.prototype.get = function () {
    return this._window;
  };

  Doc.prototype.set = function (window) {
    this._window = window;
  };

  Doc.prototype.getDoc = function () {
    var _a;

    return (_a = this.get()) === null || _a === void 0 ? void 0 : _a.document;
  };

  return Doc;
}();
"use strict";

var ExtractPDF =
/** @class */
function () {
  function ExtractPDF() {} // metodo de extrair valores de um array de objetos json


  ExtractPDF.extract = function (_a) {
    var object = _a.object,
        otherObject = _a.otherObject,
        values = _a.values;
    var keys = Object.keys(otherObject);

    if (keys.length > 0) {
      for (var _i = 0, keys_1 = keys; _i < keys_1.length; _i++) {
        var key = keys_1[_i];

        if (Object.keys(otherObject[key]).length === 0) {
          values.push(object[key]);
        }

        this.extract({
          object: object[key],
          otherObject: otherObject[key],
          values: values
        });
      }
    }
  };

  ExtractPDF.extractToKey = function (objects) {
    var update = [];

    if (objects.length % 2 === 0) {
      for (var _i = 0, objects_1 = objects; _i < objects_1.length; _i++) {
        var val = objects_1[_i];
        var now = {
          text: this.replaceVal(val)
        };
        update.push(now);
      }
    } else {
      for (var _a = 0, objects_2 = objects; _a < objects_2.length; _a++) {
        var val = objects_2[_a];
        var now = {
          text: this.replaceVal(val)
        };
        update.push(now);
      }

      update.push({
        text: ''
      });
    }

    return update;
  };

  ExtractPDF.replaceVal = function (val) {
    return val.replace(/[•]/g, '-');
  };

  ExtractPDF.extractOfArrayToKey = function (objects, object) {
    var extract = this.extractOfArray(objects, object);
    var newArray = [];

    for (var _i = 0, extract_1 = extract; _i < extract_1.length; _i++) {
      var obj = extract_1[_i];
      var update = [];

      if (obj.length % 2 === 0) {
        for (var _a = 0, obj_1 = obj; _a < obj_1.length; _a++) {
          var val = obj_1[_a];
          var now = {
            text: this.replaceVal(val)
          };
          update.push(now);
        }
      } else {
        for (var _b = 0, obj_2 = obj; _b < obj_2.length; _b++) {
          var val = obj_2[_b];
          var now = {
            text: this.replaceVal(val)
          };
          update.push(now);
        }

        update.push({
          text: ''
        });
      }

      newArray.push(update);
    }

    return newArray;
  };

  ExtractPDF.extractOfArrayNotKey = function (objects, object) {
    var extract = this.extractOfArray(objects, object);
    var newArray = [];

    for (var _i = 0, extract_2 = extract; _i < extract_2.length; _i++) {
      var obj = extract_2[_i];
      var update = [];

      for (var _a = 0, obj_3 = obj; _a < obj_3.length; _a++) {
        var val = obj_3[_a];
        update.push(this.replaceVal(val));
      }

      newArray.push(update);
    }

    return newArray;
  };

  ExtractPDF.extractOfArray = function (objects, object) {
    var extract = [];

    for (var _i = 0, objects_3 = objects; _i < objects_3.length; _i++) {
      var obj = objects_3[_i];
      var values = [];
      this.extract({
        object: obj,
        otherObject: object,
        values: values
      });
      extract.push(values);
    }

    return extract;
  };

  ExtractPDF.extractOfElements = function (labels, values, cols) {
    if (cols === void 0) {
      cols = 1;
    }

    var k = 0;
    cols = cols < 1 ? 1 : cols;
    var tValues = [];

    for (var j = 0; j < values.length; j++) {
      var tValue = {
        cols: []
      }; //@ts-ignore

      if (labels.length != values[j].length) {
        return [];
      }

      while (true) {
        var tCols = {
          elements: []
        };

        for (var y = 0; y < cols; y++) {
          //@ts-ignore 
          var label = labels[k];

          if (typeof label !== 'undefined') {
            var element = {
              label: label,
              value: values[j][k]
            };
            tCols.elements.push(element);
            k++;
          } else {
            break;
          }
        }

        tValue.cols.push(tCols); //@ts-ignore

        if (k === values[j].length) {
          k = 0;
          break;
        }
      }

      tValues.push(tValue);
    }

    return tValues;
  };

  ExtractPDF.extractMap = function (values, funct) {
    if (typeof values !== 'undefined' && typeof funct === 'function') {
      var str = "" + (values === null || values === void 0 ? void 0 : values.map(function (t, index) {
        return funct(t, index);
      }));
      return str.replace(/[,]/g, '');
    }

    return '';
  };

  return ExtractPDF;
}();
