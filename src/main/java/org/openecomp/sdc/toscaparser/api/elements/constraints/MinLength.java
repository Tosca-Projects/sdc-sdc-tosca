package org.openecomp.sdc.toscaparser.api.elements.constraints;

import org.openecomp.sdc.toscaparser.api.common.JToscaValidationIssue;

import java.util.LinkedHashMap;

import org.openecomp.sdc.toscaparser.api.utils.ThreadLocalsHolder;

public class MinLength extends Constraint {
	// Constraint class for "min_length"
	
	// Constrains the property or parameter to a value of a minimum length.

	@Override
	protected void _setValues() {

		constraintKey = MIN_LENGTH;

		validTypes.add("Integer");
		
		validPropTypes.add(Schema.STRING);
		validPropTypes.add(Schema.MAP);
		
	}
	
	public MinLength(String name,String type,Object c) {
		super(name,type,c);
		
		if(!validTypes.contains(constraintValue.getClass().getSimpleName())) {
	        ThreadLocalsHolder.getCollector().appendValidationIssue(new JToscaValidationIssue("JE113", "InvalidSchemaError: The property \"min_length\" expects an integer")); 
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected boolean _isValid(Object value) {
	    if(value instanceof String && constraintValue instanceof Integer &&
	    		((String)value).length() >= (Integer)constraintValue) {
	        return true;
	    }
	    else if(value instanceof LinkedHashMap && constraintValue instanceof Integer &&
	    		((LinkedHashMap<String,Object>)value).size() >= (Integer)constraintValue) {
	        return true;
	    }
		return false;
	}

	@Override
	protected String _errMsg(Object value) {
	    return String.format("Length of value \"%s\" of property \"%s\" must be at least \"%s\"",
	    					 value.toString(),propertyName,constraintValue.toString());
	}

}

/*python

class MinLength(Constraint):
	"""Constraint class for "min_length"
	
	Constrains the property or parameter to a value to a minimum length.
	"""
	
	constraint_key = Constraint.MIN_LENGTH
	
	valid_types = (int, )
	
	valid_prop_types = (Schema.STRING, Schema.MAP)
	
	def __init__(self, property_name, property_type, constraint):
	    super(MinLength, self).__init__(property_name, property_type,
	                                    constraint)
	    if not isinstance(self.constraint_value, self.valid_types):
	        ValidationIsshueCollector.appendException(
	            InvalidSchemaError(message=_('The property "min_length" '
	                                         'expects an integer.')))
	
	def _is_valid(self, value):
	    if ((isinstance(value, str) or isinstance(value, dict)) and
	       len(value) >= self.constraint_value):
	        return True
	
	    return False
	
	def _err_msg(self, value):
	    return (_('Length of value "%(pvalue)s" of property "%(pname)s" '
	              'must be at least "%(cvalue)s".') %
	            dict(pname=self.property_name,
	                 pvalue=value,
	                 cvalue=self.constraint_value))
*/