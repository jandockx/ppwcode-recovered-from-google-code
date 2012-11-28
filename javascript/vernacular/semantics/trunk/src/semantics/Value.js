define(["dojo/_base/declare", "./PpwCodeObject", "ppwcode/contracts/_Mixin"],
    function(declare, PpwCodeObject, _ContractMixin) {

      return declare("be.ppwcode.vernacular.semantics.Value", [PpwCodeObject, _ContractMixin], {
// immutable

        _c_invar: [

        ],

        constructor: function(/*Object*/ props) {
          this._c_pre(function() {return props /* exists and not null */;}); // TODO and is an object

          // NOP
        },

        equals: function(/*Value*/ other) {
          this._c_ABSTRACT(other);
          return false; // return boolean
        }

      });
    }
);
