define(["dojo/_base/declare",
  "ppwcode/semantics/ui/_EditableSemanticObjectDetail", "dijit/_TemplatedMixin", "dijit/_WidgetsInTemplateMixin",
  "../viewmodel/SpecialPerson",
  "dojo/text!./templates/SpecialPersonPane.html", "dojo/i18n!./nls/SpecialPerson",
  "ppwcode/semantics/ui/test/mock/presentation/PersonPane", "dijit/form/ValidationTextBox", "dojox/mvc/at"],
  function(declare,
           _SemanticObjectPane, _TemplatedMixin, _WidgetsInTemplateMixin,
           SpecialPerson,
           template, labels) {

    return declare([_SemanticObjectPane, _TemplatedMixin, _WidgetsInTemplateMixin], {

      templateString: template,
      labels: labels,

      getTargetType: function() {
        return SpecialPerson;
      },

      // summary:
      //    The widget for the SpecialPerson.email in the widget
      txtEmail: null,

      // summary
      //    The widget for the superclass, Person
      _personPane: null,

      _wrappedDetails: function() {
        return [this._personPane];
      },

      _propagateTarget: function(/*SpecialPerson*/ sp) {
        this._personPane.set("target", sp);
      }

    });

  });
