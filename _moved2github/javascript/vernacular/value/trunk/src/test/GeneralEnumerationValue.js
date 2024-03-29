/*
Copyright 2013 - $Date $ by PeopleWare n.v.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

define(["../GeneralEnumerationValue", "ppwcode-vernacular-semantics/test/Value"],
  function(GeneralEnumerationValue, testGeneratorValue) {

    function testGeneratorGeneralEnumerationValue(Constructor, kwargs1, kwargs2) {

      testGeneratorValue(Constructor, kwargs1, kwargs2);

    }

    testGeneratorGeneralEnumerationValue(
      GeneralEnumerationValue,
      {
        enumValue: "ENUM VALUE 1"
      },
      {
        enumValue: "ENUM VALUE 2"
      }
    );

    return testGeneratorGeneralEnumerationValue;
  }
);
