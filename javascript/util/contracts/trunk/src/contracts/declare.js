define(["dojo/_base/declare"], function(dojoDeclare) {
 // Documentation
  //  What triggers the process is the occurrence of a property called exactly
  // "invariants" in the "props" argument of the declare method.

  var _invariantPropertyName = "invariants";
  // hardcoded name of type invariant property

  var _contractMethodImplName = "impl";
  var _contractMethodPreName = "pre";
  var _contractMethodPostName = "post";

  function _errorMsgContractMethod(propName) {
    return "ContractMethod '" + propName + "' not well-formed: ";
  }

  function _crackParameters(className, superclass, props) {
    // summary:
    //    Returns an object with properties "className", "superclass" and "props",
    //    taking into account that in the arguments className is optional; that
    //    superclass may be null, a (constructor) Function, or an Array of
    //    (constructor) Functions; that props is an optional object.
    // returns: Object
    // description:
    //    When there is no className, the first argument is intended to be
    //    superclass, and the second argument is intended to be props.
    //    The resulting object is then
    //    {"className" : "", "superclass" : arguments[0], "props" : arguments[1]}.
    //    When there is a className, the resulting object is
    //    {"className" : arguments[0], "superclass" : arguments[1], "props" : arguments[2]}.

    // copied from dojo/_base/declare.js - declare - first 6 lines of code

    if(typeof className != "string"){
      props = superclass;
      superclass = className;
      className = "";
    }
    props = props || {};

    return {"className" : className, "superclass" : superclass, "props" : props};
  }

  var _objectProto = Object.prototype;
  var _urToStringF = _objectProto.toString;

  function _urToString(o) {
    return _urToStringF.call(o);
  }

  function _isFunction(candidateFunction) {
    return _urToString(candidateFunction) === "[object Function]";
  }

  function _isArray(candidateArray) {
    return _urToString(candidateArray) === "[object Array]";
  }

  function _isFunctionOrArray(candidate) {
    return _isFunction(candidate) || _isArray(candidate);
  }

  function _checkInvariantsWellFormed(candidateInvariants) {
    // summary:
    //    Void method, that throws an error if the candidateInvariants are not
    //    well-formed invariants.

    // TODO
  }

  function _checkPresWellFormed(candidatePres) {
    // summary:
    //    Void method, that throws an error if the candidatePres are not
    //    well-formed preconditions.

    // TODO
  }

  function _checkPostsWellFormed(candidatePosts) {
    // summary:
    //    Void method, that throws an error if the candidatePosts are not
    //    well-formed postconditions (nominal or exceptional).

    // TODO
  }

  function _buildContractMethod(method) {
    var methodText = method.toString();
    // methodText contains the text of the method.
    // In some browsers, this contains comments and eol's; in others, it doesn't.
    console.log(methodText);
    return method;
  }

  function _isContractMethod(candidateCm, propName) {
    // summary:
    //    Boolean method that returns true if candidateCm is of (duck) type
    //    ContractMethod. If it is close, but not well-formed, we throw an
    //    error.
    // description:
    //    This method returns true if:
    //    - candidateCm is an Object, i.e., not a function or an Array
    //    - candidateCm has one of the properties impl, pre or post
    //    It then is an error when
    //    - candidateCm does not have all 3 properties impl, pre, post
    //    - impl is not a Function
    //    - _checkPresWellFormed(candidateCm.pre) fails
    //    - _checkPostsWellFormed(candidateCm.post) fails

    var result = candidateCm && (! _isFunctionOrArray(candidateCm)) &&
                   (candidateCm.hasOwnProperty(_contractMethodImplName) ||
                     candidateCm.hasOwnProperty(_contractMethodPreName) ||
                     candidateCm.hasOwnProperty(_contractMethodPostName));

    if (result) {
      if (! candidateCm.hasOwnProperty(_contractMethodImplName)) {
        throw new SyntaxError(_errorMsgContractMethod(propName) + _contractMethodImplName + " not defined");
      }
      if (! candidateCm.hasOwnProperty(_contractMethodPreName)) {
        throw new SyntaxError(_errorMsgContractMethod(propName) + _contractMethodPreName + " not defined");
      }
      if (! candidateCm.hasOwnProperty(_contractMethodPostName)) {
        throw new SyntaxError(_errorMsgContractMethod(propName) + _contractMethodPostName+ " not defined");
      }
      if (! _isFunction(candidateCm[_contractMethodImplName])) {
        throw new SyntaxError(_errorMsgContractMethod(propName) + _contractMethodImplName+ " not a Function");
      }
      _checkPresWellFormed(candidateCm[_contractMethodPreName]);
      _checkPostsWellFormed(candidateCm[_contractMethodPostName]);
    }

    return result;
  }

  function ppwcodeDeclare(className, superclass, props) {
    var trueArgs = _crackParameters(className, superclass, props);
    /* we normalize props, so that we are sure that
     * - a property "invariants" contains sensible invariants
     * - if the value of a property with name pn of props (props[pn]) is an object cm that
     *   has (duck) type ContractMethod,
     *   -- the actual function (cm.impl) is a function, and associated to the property name
     *      (in stead of cm)
     *   -- the preconditions (cm.pre) are sensible preconditions, and associated to the
     *      pre property of the method in props (props[pn].pre)
     *   -- the postconditions (cm.post) are sensible postconditions, and associated to the
     *      post property of the method in props (props[pn].post)
     *
     */
    var trueProps = trueArgs.props;
    if (trueProps.hasOwnProperty(_invariantPropertyName)) {
      var propNames = Object.getOwnPropertyNames(trueProps);
      propNames.forEach(function(propName) {
        var propValue = trueProps[propName];
        if (propName === _invariantPropertyName) {
          _checkInvariantsWellFormed(propValue);
        }
        else if (_isFunction(propValue)) {
          trueProps[propName] = _buildContractMethod(propValue);
        }
//        else if (_isContractMethod(propValue, propName)) {
//          var actualMethod = propValue[_contractMethodImplName];
//          actualMethod[_contractMethodPreName] = propValue[_contractMethodPreName];
//          actualMethod[_contractMethodImplName] = actualMethod;
//          actualMethod[_contractMethodPostName] = propValue[_contractMethodPostName];
//          trueProps[propName] = actualMethod;
//        }
      });
    }
    return dojoDeclare(trueArgs.className, trueArgs.superclass, trueProps);
  }

  var contracts = {
    pre     : precondition,
    post    : postcondition,
    excp    : excpcondition,
    declare : dojoDeclare
  };

  return contracts;

});
