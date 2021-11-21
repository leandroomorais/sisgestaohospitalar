"use strict";

var TemplateDoc =
/** @class */
function () {
  function TemplateDoc(document) {
    var _this = this;

    this.document = null;

    this.createStyle = function (_a) {
      var _b, _c, _d;

      var value = _a.value;

      if (typeof value !== "undefined" && typeof value === 'string') {
        (_b = _this.document) === null || _b === void 0 ? void 0 : _b.write("<style>");
        (_c = _this.document) === null || _c === void 0 ? void 0 : _c.write(value);
        (_d = _this.document) === null || _d === void 0 ? void 0 : _d.write("</style>");
      }
    };

    this.openBody = function () {
      var _a;

      (_a = _this.document) === null || _a === void 0 ? void 0 : _a.write("<body>");
    };

    this.closeBody = function () {
      var _a;

      (_a = _this.document) === null || _a === void 0 ? void 0 : _a.write("</body>");
    };

    this.openHtml = function (_a) {
      var _b;

      var title = _a.title,
          links = _a.links,
          scripts = _a.scripts;
      (_b = _this.document) === null || _b === void 0 ? void 0 : _b.write("<!DOCTYPE html>\n        <html lang=\"pt-br\">\n        \n        <head>\n            <meta charset=\"UTF-8\">\n            <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n            <title>" + title + "</title>\n            " + ExtractPDF.extractMap(links, function (t, index) {
        return "<link href='" + t.href + "' ref='" + t.rel + "' type='" + t.type + "'/>";
      }) + "\n            " + ExtractPDF.extractMap(scripts, function (t, index) {
        return "<script src='" + t.src + "' type='" + t.type + "'></script>";
      }) + "\n        </head>");
    };

    this.closeHtml = function () {
      var _a;

      (_a = _this.document) === null || _a === void 0 ? void 0 : _a.write("</html>");
    };

    this.createElements = function (_a) {
      var values = _a.values,
          onRender = _a.onRender;

      if (onRender) {
        values === null || values === void 0 ? void 0 : values.map(function (value, index) {
          return _this.createElement(onRender(value, index));
        });
      }
    };

    this.createElement = function (innerHTML) {
      var _a;

      (_a = _this.document) === null || _a === void 0 ? void 0 : _a.write(innerHTML);
    };
    /* createTitle = ({ value, attrsChild, attrsFather, attrsFatherParent }: TParameter) => {
        this.create("p", attrsChild, attrsFather, attrsFatherParent, value, this.document)
    }; */

    /* createHeader = ({ value, attrsChild, attrsFatherParent }: TParameter) => {
        this.create("p", attrsChild, { class: 'header' }, attrsFatherParent, value, this.document)
    };
         createText = ({ value, attrsChild, attrsFather, attrsFatherParent }: TParameter) => {
        this.create("p", attrsChild, attrsFather, attrsFatherParent, value, this.document)
    };
         createLineVertical = ({ attrsChild, attrsFather, attrsFatherParent }: TParameter) => {
        this.create("hr", attrsChild, attrsFather, attrsFatherParent, undefined, this.document)
    };
         private create(tagChild: string, attrsChild: {} | undefined, attrsFather: {} | undefined, attrsFatherParent: {} | undefined, value: string | number | object | undefined, document: Document | undefined | null) {
        //@ts-ignore
        const tag = new Tag({ tagName: tagChild, attrs: attrsChild, value: value })
        //@ts-ignore
        const div = new Tag({ tagName: 'div', attrs: attrsFather, children: [tag] })
        //@ts-ignore
        const divSpace = new Tag({ tagName: 'div', attrs: attrsFatherParent, children: [div] })
        //@ts-ignore
        const divParent = new Tag({ tagName: 'div', children: [divSpace] })
        //@ts-ignore
        const divView = new TagView(divParent)
        //@ts-ignore
        const element: Element = divView.element
        document?.write(element.innerHTML)
    } */


    this.createHTML = function (_a) {
      var _b;

      var innerHTML = _a.innerHTML;
      (_b = _this.document) === null || _b === void 0 ? void 0 : _b.write(innerHTML);
    };

    this.createScript = function (_a) {
      var _b;

      var src = _a.src,
          type = _a.type; //@ts-ignore

      var script = new Tag({
        tagName: 'script',
        attrs: {
          src: src,
          type: type
        }
      });
      View.append(new TagView(script).element, (_b = _this.document) === null || _b === void 0 ? void 0 : _b.body);
    };

    this.createLink = function (_a) {
      var _b;

      var href = _a.href,
          rel = _a.rel,
          type = _a.type; //@ts-ignore

      var link = new Tag({
        tagName: 'link',
        attrs: {
          href: href,
          rel: rel,
          type: type
        }
      });
      View.append(new TagView(link).element, (_b = _this.document) === null || _b === void 0 ? void 0 : _b.head);
    };

    this.document = document;
  }

  return TemplateDoc;
}();
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
