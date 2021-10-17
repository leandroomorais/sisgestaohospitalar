"use strict";

var Model = function () {
  function Model(_a) {
    var tagName = _a.tagName,
        attrs = _a.attrs,
        value = _a.value,
        children = _a.children;
    this._tagName = tagName;
    this._attrs = attrs;
    this._value = value;
    this._children = children;
  }

  Object.defineProperty(Model.prototype, "tagName", {
    get: function get() {
      return this._tagName;
    },
    set: function set(value) {
      this._tagName = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(Model.prototype, "attrs", {
    get: function get() {
      return this._attrs;
    },
    set: function set(value) {
      this._attrs = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(Model.prototype, "value", {
    get: function get() {
      return this._value;
    },
    set: function set(value) {
      this._value = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(Model.prototype, "children", {
    get: function get() {
      return this._children;
    },
    set: function set(value) {
      this._children = value;
    },
    enumerable: false,
    configurable: true
  });
  return Model;
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

var Tag = function (_super) {
  __extends(Tag, _super);

  function Tag(_a) {
    var tagName = _a.tagName,
        value = _a.value,
        attrs = _a.attrs,
        children = _a.children;
    return _super.call(this, {
      tagName: tagName,
      value: value,
      attrs: attrs,
      children: children
    }) || this;
  }

  return Tag;
}(Model);
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

var Col = function (_super) {
  __extends(Col, _super);

  function Col(_a) {
    var tagName = _a.tagName,
        value = _a.value,
        attrs = _a.attrs,
        children = _a.children;
    return _super.call(this, {
      tagName: tagName ? tagName : "td",
      value: value,
      attrs: attrs,
      children: children
    }) || this;
  }

  return Col;
}(Model);
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

var Row = function (_super) {
  __extends(Row, _super);

  function Row(_a) {
    var tagName = _a.tagName,
        attrs = _a.attrs,
        value = _a.value,
        children = _a.children;
    return _super.call(this, {
      tagName: tagName ? tagName : 'tr',
      attrs: attrs,
      value: value,
      children: children
    }) || this;
  }

  return Row;
}(Model);
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

var THead = function (_super) {
  __extends(THead, _super);

  function THead(_a) {
    var tagName = _a.tagName,
        value = _a.value,
        children = _a.children,
        attrs = _a.attrs;

    var _this = _super.call(this, {
      tagName: tagName ? tagName : 'thead',
      value: value,
      attrs: attrs,
      children: children
    }) || this;

    _this._rows = children;
    return _this;
  }

  Object.defineProperty(THead.prototype, "rows", {
    get: function get() {
      return this._rows;
    },
    set: function set(value) {
      this._rows = value;
    },
    enumerable: false,
    configurable: true
  });
  return THead;
}(Model);
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

var TBody = function (_super) {
  __extends(TBody, _super);

  function TBody(_a) {
    var tagName = _a.tagName,
        value = _a.value,
        attrs = _a.attrs,
        children = _a.children;

    var _this = _super.call(this, {
      tagName: tagName ? tagName : 'tbody',
      value: value,
      attrs: attrs,
      children: children
    }) || this;

    _this._rows = children;
    return _this;
  }

  Object.defineProperty(TBody.prototype, "rows", {
    get: function get() {
      return this._rows;
    },
    set: function set(value) {
      this._rows = value;
    },
    enumerable: false,
    configurable: true
  });
  return TBody;
}(Model);
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

var TFooter = function (_super) {
  __extends(TFooter, _super);

  function TFooter(_a) {
    var tagName = _a.tagName,
        value = _a.value,
        children = _a.children,
        attrs = _a.attrs;

    var _this = _super.call(this, {
      tagName: tagName ? tagName : "tfooter",
      value: value,
      attrs: attrs,
      children: children
    }) || this;

    _this._rows = children;
    return _this;
  }

  Object.defineProperty(TFooter.prototype, "rows", {
    get: function get() {
      return this._rows;
    },
    set: function set(value) {
      this._rows = value;
    },
    enumerable: false,
    configurable: true
  });
  return TFooter;
}(Model);
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

var Table = function (_super) {
  __extends(Table, _super);

  function Table(_a) {
    var tagName = _a.tagName,
        attrs = _a.attrs,
        value = _a.value,
        children = _a.children;

    var _this = _super.call(this, {
      tagName: tagName ? tagName : 'table',
      value: value,
      children: children,
      attrs: attrs
    }) || this;

    if (children) {
      if ((children === null || children === void 0 ? void 0 : children.length) > 0) {
        for (var _i = 0, children_1 = children; _i < children_1.length; _i++) {
          var model = children_1[_i];

          if (model instanceof THead) {
            _this._thead = model;
          } else if (model instanceof TBody) {
            _this._tbody = model;
          } else if (model instanceof TFooter) {
            _this._tfooter = model;
          }
        }
      }
    }

    return _this;
  }

  Object.defineProperty(Table.prototype, "thead", {
    get: function get() {
      return this._thead;
    },
    set: function set(value) {
      this._thead = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(Table.prototype, "tbody", {
    get: function get() {
      return this._tbody;
    },
    set: function set(value) {
      this._tbody = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(Table.prototype, "tfooter", {
    get: function get() {
      return this._tfooter;
    },
    set: function set(value) {
      this._tfooter = value;
    },
    enumerable: false,
    configurable: true
  });
  return Table;
}(Model);
"use strict";

var EventView = function () {
  function EventView() {}

  EventView.create = function (_a) {
    var type = _a.type,
        element = _a.element,
        callback = _a.callback,
        tagView = _a.tagView;

    if (type !== undefined && element !== undefined && callback !== undefined) {
      element.addEventListener(type, callback, false);
      return;
    } else if (tagView !== undefined && tagView instanceof TagView) {
      var attrs = Attrs.get(tagView.tag.attrs, 'on');

      if (attrs.length > 0) {
        for (var _i = 0, attrs_1 = attrs; _i < attrs_1.length; _i++) {
          var attr = attrs_1[_i];
          var key = Object.keys(attr)[0]; //@ts-ignore

          EventView.create({
            type: key,
            element: tagView.element,
            callback: attrs[0][key]
          });
        }
      }

      return;
    }
  };

  return EventView;
}();
"use strict";

var Attrs = function () {
  function Attrs() {}

  Attrs.set = function (element, attrs) {
    if (attrs && element) {
      var objs = this.ignore(attrs, 'on');

      for (var _i = 0, objs_1 = objs; _i < objs_1.length; _i++) {
        var obj = objs_1[_i];
        var keys = Object.keys(obj);

        for (var _a = 0, keys_1 = keys; _a < keys_1.length; _a++) {
          var key = keys_1[_a]; // @ts-ignore

          element.setAttribute(key, attrs[key]);
        }
      }
    }
  };

  Attrs.createProps = function (obj, key, value) {
    Object.defineProperty(obj, key, {
      value: value,
      enumerable: true,
      configurable: true
    });
  };

  Attrs.get = function (attrs, prefix) {
    if (attrs !== undefined) {
      var keys = Object.keys(attrs);

      if (keys.length > 0) {
        var newAttrs = [];

        for (var _i = 0, keys_2 = keys; _i < keys_2.length; _i++) {
          var key = keys_2[_i];

          if (key.search(prefix) > -1) {
            var obj = {}; //@ts-ignore

            this.createProps(obj, key.split(prefix)[1], attrs[key]); // @ts-ignore

            newAttrs.push(obj);
          }
        }

        return newAttrs;
      }
    }

    return [];
  };

  Attrs.ignore = function (attrs, prefix) {
    if (attrs !== undefined) {
      var keys = Object.keys(attrs);

      if (keys.length > 0) {
        var newAttrs = [];

        for (var _i = 0, keys_3 = keys; _i < keys_3.length; _i++) {
          var key = keys_3[_i];

          if (key.search(prefix) == -1) {
            var obj = {}; // @ts-ignore

            this.createProps(obj, key.split(prefix)[1], attrs[key]); // @ts-ignore

            newAttrs.push(obj);
          }
        }

        return newAttrs;
      }
    }

    return [];
  };

  return Attrs;
}();
"use strict";

var ElementView = function () {
  function ElementView(element) {
    if (element) {
      this._type = element.tagName;
      this._attrs = element.attrs;
      this._element = View.create(element.tagName);
      Attrs.set(this.element, element.attrs);
    }
  }

  Object.defineProperty(ElementView.prototype, "type", {
    /*getters e settes da class ElementView*/
    get: function get() {
      return this._type;
    },
    set: function set(value) {
      this._type = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(ElementView.prototype, "attrs", {
    get: function get() {
      return this._attrs;
    },
    set: function set(value) {
      this._attrs = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(ElementView.prototype, "element", {
    get: function get() {
      return this._element;
    },
    enumerable: false,
    configurable: true
  });

  ElementView.prototype.appendChild = function (element) {
    if (this.element && element) {
      return View.append(element, this.element);
    }
  };

  ElementView.prototype.removeAll = function () {
    if (this.element) {
      if (this.element.children.length > 0) {
        var children = this.element.children;

        for (var i = 0; i < children.length; i++) {
          var child = children.item(i);

          if (child) {
            child.remove();
            i--;
          }
        }
      }

      return this.element.children.length === 0;
    }
  };

  ElementView.prototype.removeChildByIndex = function (j) {
    if (this.element) {
      if (this.element.children.length > 0) {
        if (this.element.children.item(j)) {
          var child = this.element.children.item(j);

          if (child) {
            child.remove();
            return true;
          }
        }
      }
    }

    return false;
  };

  ElementView.prototype.removeChildByElement = function (element) {
    if (this.element) {
      if (this.element.children.length > 0) {
        var children = this.element.children;

        for (var i = 0; i < children.length; i++) {
          var child = children.item(i);

          if (child === element) {
            child.remove();
            return true;
          }
        }
      }
    }

    return false;
  };

  ElementView.prototype.replaceChildByIndex = function (j, newElement) {
    if (this.element) {
      if (this.element.children.length > 0) {
        if (this.removeChildByIndex(j)) {
          var element = this.element.children.item(j);
          View.replaceChild(element, newElement, this.element);
        }
      }
    }
  };

  ElementView.prototype.replaceChildByElement = function (element, newElement) {
    if (this.element) {
      if (this.element.children.length > 0) {
        View.replaceChild(element, newElement, this.element);
      }
    }
  };

  return ElementView;
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

var TagView = function (_super) {
  __extends(TagView, _super);

  function TagView(tag) {
    var _this = _super.call(this, tag) || this;

    if (tag.value) {
      _this.check(_this.element, tag.value);
    }

    if (tag.children) {
      if (tag.children.length > 0) {
        for (var i = 0; i < tag.children.length; i++) {
          var child = new TagView(tag.children[i]);
          EventView.create({
            tagView: child
          });

          _this.appendChild(child.element);
        }
      }
    }

    _this._tag = tag;
    EventView.create({
      tagView: _this
    });
    return _this;
  }

  Object.defineProperty(TagView.prototype, "tag", {
    get: function get() {
      return this._tag;
    },
    set: function set(value) {
      this._tag = value;
    },
    enumerable: false,
    configurable: true
  });

  TagView.prototype.check = function (tag, value) {
    if (tag) {
      if (tag instanceof HTMLInputElement) {
        tag.value = value;
        return;
      }

      tag.innerText = value;
    }
  };

  return TagView;
}(ElementView);
"use strict";

var View = function () {
  function View() {}

  View.create = function (tagName) {
    if (tagName) {
      return window.document.createElement(tagName);
    }
  };

  View.appendChild = function (tagName, father) {
    var child = this.create(tagName);

    if (child) {
      return father.appendChild(child);
    }
  };

  View.get = function (id) {
    return window.document.getElementById(id);
  };

  View.replaceChild = function (element, newElement, father) {
    for (var i = 0; i < father.children.length; i++) {
      var child = father.children.item(i);

      if (child) {
        if (child === element) {
          father.replaceChild(newElement, child);
          break;
        }
      }
    }
  };

  View.append = function (element, father) {
    father.appendChild(element);
    return element;
  };

  return View;
}();
"use strict";

var ElementTable = function () {
  function ElementTable() {}

  ElementTable.insert = function (model, element) {
    var _a, _b;

    var rowViews = [];

    if (model && model.rows) {
      if (((_a = model === null || model === void 0 ? void 0 : model.rows) === null || _a === void 0 ? void 0 : _a.length) > 0) {
        var rowIndex = 0;

        for (var _i = 0, _c = model.rows; _i < _c.length; _i++) {
          var row = _c[_i];
          var rowView = new RowView(row, rowIndex);

          if (row === null || row === void 0 ? void 0 : row.children) {
            if (((_b = row === null || row === void 0 ? void 0 : row.children) === null || _b === void 0 ? void 0 : _b.length) > 0) {
              var index = 0;

              for (var _d = 0, _e = row === null || row === void 0 ? void 0 : row.children; _d < _e.length; _d++) {
                var col = _e[_d];
                var colView = new ColView(col, index);
                rowView.appendChild(colView.element);
                rowView.columns.push(colView);
                index++;
              }
            }

            rowViews.push(rowView);
            element.appendChild(rowView === null || rowView === void 0 ? void 0 : rowView.element);
            rowIndex++;
          }
        }
      }
    }

    return rowViews;
  };

  return ElementTable;
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

var ColView = function (_super) {
  __extends(ColView, _super);

  function ColView(element, index) {
    var _a;

    var _this = _super.call(this, element) || this;

    var value = null;

    if (index) {
      _this._index = index;
    }

    if (element.value) {
      if (element.value instanceof Model) {
        var child = new TagView(element.value);
        value = child.tag;

        _this.appendChild(child.element);
      } else {
        var p = View.create("p");

        if (p) {
          p.textContent = element.value.toString();

          _this.appendChild(p);

          value = p.textContent;

          if (!element.children) {
            element.children = [];
            (_a = element.children) === null || _a === void 0 ? void 0 : _a.push(new Model({
              value: value,
              tagName: p.tagName
            }));
          }
        }
      }
    }

    _this._value = value;
    _this._col = element;
    return _this;
  }

  Object.defineProperty(ColView.prototype, "col", {
    get: function get() {
      return this._col;
    },
    set: function set(value) {
      this._col = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(ColView.prototype, "value", {
    get: function get() {
      return this._value;
    },
    set: function set(value) {
      this._value = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(ColView.prototype, "index", {
    get: function get() {
      return this._index;
    },
    set: function set(value) {
      this._index = value;
    },
    enumerable: false,
    configurable: true
  });
  return ColView;
}(ElementView);
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

var RowView = function (_super) {
  __extends(RowView, _super);

  function RowView(element, index) {
    var _this = _super.call(this, element) || this;

    _this._columns = [];
    _this._row = element;

    if (index) {
      _this._index = index;
    }

    return _this;
  }

  Object.defineProperty(RowView.prototype, "columns", {
    get: function get() {
      return this._columns;
    },
    set: function set(value) {
      this._columns = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(RowView.prototype, "row", {
    get: function get() {
      return this._row;
    },
    set: function set(value) {
      this._row = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(RowView.prototype, "index", {
    get: function get() {
      return this._index;
    },
    set: function set(value) {
      this._index = value;
    },
    enumerable: false,
    configurable: true
  });
  return RowView;
}(ElementView);
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

var THeadView = function (_super) {
  __extends(THeadView, _super);

  function THeadView(element) {
    var _this = _super.call(this, element) || this;

    _this._thead = element;
    _this._rows = ElementTable.insert(_this._thead, _this);
    return _this;
  }

  Object.defineProperty(THeadView.prototype, "thead", {
    get: function get() {
      return this._thead;
    },
    set: function set(value) {
      this._thead = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(THeadView.prototype, "rows", {
    get: function get() {
      return this._rows;
    },
    set: function set(value) {
      this._rows = value;
    },
    enumerable: false,
    configurable: true
  });
  return THeadView;
}(ElementView);
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

var TBodyView = function (_super) {
  __extends(TBodyView, _super);

  function TBodyView(element) {
    var _this = _super.call(this, element) || this;

    _this._tbody = element;
    _this._rows = ElementTable.insert(_this._tbody, _this);
    return _this;
  }

  Object.defineProperty(TBodyView.prototype, "tbody", {
    get: function get() {
      return this._tbody;
    },
    set: function set(value) {
      this._tbody = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(TBodyView.prototype, "rows", {
    get: function get() {
      return this._rows;
    },
    set: function set(value) {
      this._rows = value;
    },
    enumerable: false,
    configurable: true
  });
  return TBodyView;
}(ElementView);
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

var TFooterView = function (_super) {
  __extends(TFooterView, _super);

  function TFooterView(element) {
    var _this = _super.call(this, element) || this;

    _this._tfooter = element;
    _this._rows = ElementTable.insert(_this._tfooter, _this);
    return _this;
  }

  Object.defineProperty(TFooterView.prototype, "tfooter", {
    get: function get() {
      return this._tfooter;
    },
    set: function set(value) {
      this._tfooter = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(TFooterView.prototype, "rows", {
    get: function get() {
      return this._rows;
    },
    set: function set(value) {
      this._rows = value;
    },
    enumerable: false,
    configurable: true
  });
  return TFooterView;
}(ElementView);
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

var TableView = function (_super) {
  __extends(TableView, _super);

  function TableView(element, theadView, tbodyView, tfooterView) {
    var _this = _super.call(this, element) || this;

    if (theadView) {
      _this.appendChild(theadView.element);

      _this._theadView = theadView;
    }

    if (tbodyView) {
      _this.appendChild(tbodyView.element);

      _this._tbodyView = tbodyView;
    }

    if (tfooterView) {
      _this.appendChild(tfooterView.element);

      _this._tfooterView = tfooterView;
    }

    _this._table = element;
    return _this;
  }

  Object.defineProperty(TableView.prototype, "theadView", {
    get: function get() {
      return this._theadView;
    },
    set: function set(value) {
      this._theadView = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(TableView.prototype, "tbodyView", {
    get: function get() {
      return this._tbodyView;
    },
    set: function set(value) {
      this._tbodyView = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(TableView.prototype, "tfooterView", {
    get: function get() {
      return this._tfooterView;
    },
    set: function set(value) {
      this._tfooterView = value;
    },
    enumerable: false,
    configurable: true
  });
  Object.defineProperty(TableView.prototype, "table", {
    get: function get() {
      return this._table;
    },
    set: function set(value) {
      this._table = value;
    },
    enumerable: false,
    configurable: true
  });
  return TableView;
}(ElementView);
"use strict";

var TableCreate = function () {
  function TableCreate() {}

  TableCreate.create = function (_a) {
    var id = _a.id,
        thead = _a.thead,
        tbody = _a.tbody,
        tfooter = _a.tfooter,
        options = _a.options;
    return this.createTable(id, thead, tbody, tfooter, options);
  };

  TableCreate.createTable = function (id, head, body, footer, options) {
    var _a, _b, _c, _d, _e;

    var divTable = View.get(id.toString());

    if (divTable) {
      var rowTHeadOption = options === null || options === void 0 ? void 0 : options.rowTHead;
      var rowTBodyOption = options === null || options === void 0 ? void 0 : options.rowTBody;
      var rowTFooterOption = options === null || options === void 0 ? void 0 : options.rowTFooter;
      var colTHeadOption = options === null || options === void 0 ? void 0 : options.colTHead;
      var colTBodyOption = options === null || options === void 0 ? void 0 : options.colTBody;
      var colTFooterOption = options === null || options === void 0 ? void 0 : options.colTFooter;
      var theadOption = options === null || options === void 0 ? void 0 : options.thead;
      var tbodyOption = options === null || options === void 0 ? void 0 : options.tbody;
      var tfooterOption = options === null || options === void 0 ? void 0 : options.tfooter;
      var tableOption = options === null || options === void 0 ? void 0 : options.table;
      var attrsRowHead = null;
      var attrsRowBody = null;
      var attrsRowFooter = null;
      var attrsColHead = null;
      var attrsColBody = null;
      var attrsColFooter = null;
      var attrsThead = null;
      var attrsTbody = null;
      var attrsTfooter = null;
      var attrsTable = null;
      var thead = null;
      var tbody = null;
      var tfooter = null;
      var tbodyView = null;
      var theadView = null;
      var tfooterView = null;

      if (rowTFooterOption) {
        attrsRowFooter = rowTFooterOption;
      }

      if (rowTBodyOption) {
        attrsRowBody = rowTBodyOption;
      }

      if (rowTHeadOption) {
        attrsRowHead = rowTHeadOption;
      }

      if (colTBodyOption) {
        attrsColBody = colTBodyOption;
      }

      if (colTHeadOption) {
        attrsColHead = colTHeadOption;
      }

      if (colTFooterOption) {
        attrsColFooter = colTFooterOption;
      }

      if (theadOption) {
        attrsThead = theadOption;
      }

      if (tbodyOption) {
        attrsTbody = tbodyOption;
      }

      if (tfooterOption) {
        attrsTfooter = tfooterOption;
      }

      if (tableOption) {
        attrsTable = tableOption;
      }

      if (head) {
        var rowHead = new Row({
          attrs: attrsRowHead ? attrsRowHead : {
            scope: 'col'
          },
          children: []
        });
        thead = new THead({
          children: [rowHead],
          attrs: attrsThead ? attrsThead : undefined
        });

        for (var i = 0; i < head.length; i++) {
          var col = new Col({
            value: head[i],
            tagName: 'th',
            attrs: attrsColHead ? attrsColHead : {
              scope: 'col'
            }
          });
          (_a = rowHead.children) === null || _a === void 0 ? void 0 : _a.push(col);
        }

        theadView = new THeadView(thead);
      }

      if (body) {
        tbody = new TBody({
          children: [],
          attrs: attrsTbody ? attrsTbody : undefined
        });

        for (var i = 0; i < body.length; i++) {
          var row = new Row({
            tagName: 'tr',
            children: [],
            attrs: attrsRowBody ? attrsRowBody : undefined
          });

          for (var j = 0; j < body[i].length; j++) {
            var col = new Col({
              value: body[i][j],
              tagName: 'td',
              attrs: attrsColBody ? attrsColBody : {
                scope: 'col'
              }
            });
            (_b = row.children) === null || _b === void 0 ? void 0 : _b.push(col);
          }

          (_c = tbody.rows) === null || _c === void 0 ? void 0 : _c.push(row);
        }

        tbodyView = new TBodyView(tbody);
      }

      if (footer) {
        tfooter = new TFooter({
          children: [],
          attrs: attrsTfooter ? attrsTfooter : undefined
        });

        for (var i = 0; i < footer.length; i++) {
          var row = new Row({
            tagName: 'tr',
            children: [],
            attrs: attrsRowFooter ? attrsRowFooter : undefined
          });

          for (var j = 0; j < footer[i].length; j++) {
            var col = new Col({
              value: footer[i][j],
              tagName: 'td',
              attrs: attrsColFooter ? attrsColFooter : {
                scope: 'col'
              }
            });
            (_d = row.children) === null || _d === void 0 ? void 0 : _d.push(col);
          }

          (_e = tfooter.rows) === null || _e === void 0 ? void 0 : _e.push(row);
        }

        tfooterView = new TFooterView(tfooter);
      }

      var children = [];

      if (thead) {
        children.push(thead);
      }

      if (tbody) {
        children.push(tbody);
      }

      if (tfooter) {
        children.push(tfooter);
      }

      var tableM = new Table({
        children: children,
        attrs: attrsTable ? attrsTable : undefined
      });
      var tableView = new TableView(tableM, theadView, tbodyView, tfooterView);
      View.append(tableView.element, divTable);
      return tableView;
    }

    return null;
  };

  return TableCreate;
}();
