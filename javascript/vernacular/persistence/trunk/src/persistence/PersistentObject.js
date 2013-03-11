define(["dojo/_base/declare", "ppwcode/semantics/SemanticObject", "dojo/_base/lang"],
  function(declare, SemanticObject, lang) {

    function internalReload(/*PersistentObject*/ self, /*Object*/ json) {
      if (json && json.persistenceId /* TODO json.persistenceId undefined, but not null */) {
        if (self.persistenceId && json.persistenceId != null && json.persistenceId != self.persistenceId) {
          throw "ERROR: cannot change from an existing persistenceId to another (" +
            json.toString() + ")"; // TODO precondition
        }
        // this will happen with the JSON response from a creation or IdNotFoundException, and during construction
        //noinspection JSUnresolvedFunction
        self._changeAttrValue("persistenceId", json.persistenceId);
      }
    }

    var PersistentObject = declare([SemanticObject], {

      _c_invar: [
        function() {return this._c_prop_mandatory("persistenceType");},
        function() {return this._c_prop_string("persistenceType");},
        function() {return this.hasOwnProperty("persistenceId");},
        /* we don't care about the format of the persistenceId here; we just keep it, and return it to the server
         like we got it. */
        function() {return this.get("persistenceId") === null || lang.isString(this.getKey());}
      ],

      // persistenceType: String
      //   Precise name of the type of this object in server lingo.
      //   This is needed for create, update, and delete, where the server
      //   methods are polymorph.
      //   (Note: this is currently so, but in general, this is not needed.
      //    With good rest we should pass the type for update, create and delete
      //    via the URL. The other way around is necessary, for a polymorph retrieve.
      //    Consider this a fix for now.
      //    IDEA solve this on the server)
      //   This property should be set on the prototype of each type, and not be
      //   changed ever. It should never have a value on the level of the instance.
      persistenceType: null,

      // persistenceId: Object
      //   Normally a number. The primary key of this object in the server.
      //   Before the object is created in persistent storage, it must be null.
      //   That is how we know the object is fresh.
      //   Afterwards, it may never change. It is under control of the server.
      //   We actually don't care what type this is. We just store, and return to the server.
      persistenceId: null,

      constructor: function(/*Object*/ props) {
        /* we don't care about the format of the persistenceId here; we just keep it, and return it to the server
         like we got it. */

        this.persistenceId = null; // init for when there are no props
        internalReload(this, props);
      },

      reload: function(/*Object*/ json) {
        // persistenceId can change from null to an actual number
        internalReload(this, json);
      },

      _persistenceTypeSetter: function() {
        // persistenceId is read-only
        throw "ERROR: persistence type may only be set in the type definition";
      },

      _persistenceIdSetter: function() {
        // persistenceId is read-only
        throw "ERROR: the persistence id can never change";
      },

      getKey: function() {
        // summary:
        //   A (business) key (String) that uniquely identifies
        //   the object represented by this (if we all keep to the rules).
        //   Can only be called when this.get("persistenceId") !== null.

        this._c_pre(function() {return this && this.get("persistenceId") != null;});

        return PersistentObject.keyForObject(this);
      },

      _extendJsonObject: function(/*Object*/ json) {
        json["$type"] = this.persistenceType; // "$type" is a magic string, mandated by JSON.net TODO fix this
        json.persistenceId = this.persistenceId;
      },

      getTypeDescription: function() {
        // summary:
        //   For PersistentObject instances, this is the persistenceType, which should exist
        //   in concrete classes at least.
        // description:
        //   The superclass implementation is used as fallback, as toString is often used in debug.
        return this.persistenceType ? this.persistenceType : this.inherited(arguments);
      },

      _stateToString: function(/*Array of String*/ toStrings) {
        toStrings.push("persistenceId: " + this.persistenceId);
      }
    });



    PersistentObject.keyForId = function(/*Function*/ SubType, /*Number*/ id) {
      // IDEA can't use current form of precondition here
      if (! (SubType && lang.isFunction(SubType))) {
        throw new Error("precondition violation: SubType && lang.isFunction(SubType)");
      }
      // IDEA must be a subtype of PeristentObject
      if (! (SubType.prototype.persistenceType)) {
        throw new Error("precondition violation: SubType.prototype.persistenceType");
      }
      if (! (id)) {
        throw new Error("precondition violation: id");
      }

      var serverType = SubType.prototype.persistenceType;

      var result = serverType + "@" + id;
      return result; // return String
    };

    PersistentObject.keyForObject = function(/*PersistentObject*/ po) {
      // summary:
      //   po --> String
      //   A function that returns a unique (business) key (String) that uniquely identifies
      //   the object represented by po (if we all keep to the rules).
      //   Can only be called when po.get("persistenceId") !== null.

      // IDEA can't use current form of precondition here
      if (! (po && po.get("persistenceId") != null)) {
        throw new Error("precondition violation: po && po.get('persistenceId') != null");
      }

      var Constructor = po.constructor;
      return PersistentObject.keyForId(Constructor, po.get("persistenceId")); // return String
    };



    return PersistentObject; // return PersistentObject
  }
);
