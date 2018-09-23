/**
 * This script is for the trainer import questions page. 
 * 
 * The variables are not allowed in js files as the jstl < tag will throw an error while importing, these variables(technologies, topics, tests)
 * are held in the original jsp file above this imported js file
 * 
 *  
 * It allows the dropboxes to interact with one another
 * if a technology is changed then the topic dropdown will change and in turn the test dropdown will change
 * if only the topic dropdown changes only the test dropdown will change
 * 
 * @author phoutsaksit.keomala
 */
	function changeTopicDropdown(techSelect) {
		var techId = techSelect.value;
		var topicSelect = document.getElementById("topic-select");
		removeOptions(topicSelect);
		
		for(var t=0; t<topics.length; t++) {
			if(topics[t].techId === techId) {
				var opt = document.createElement('option');
			    opt.value = topics[t].id;
			    opt.innerHTML = topics[t].name;
			    topicSelect.appendChild(opt);
			}
		}
		
		if(topicSelect.options.length == 0) {
			var opt = document.createElement('option');
		    opt.innerHTML = "No topics to list!";
		    topicSelect.appendChild(opt);
		    
		    
			var testSelect = document.getElementById("test-select");
		    removeOptions(testSelect);
		    var opt = document.createElement('option');
		    opt.innerHTML = "No tests to list!";
		    testSelect.appendChild(opt);
		} else {
			changeTestDropdown(topicSelect);			
		}
	}
	
	
	function changeTestDropdown(topicSelect) {
		var topicId = topicSelect.value;
		var testSelect = document.getElementById("test-select");
		removeOptions(testSelect);
		
		for(var t=0; t<tests.length; t++) {
			if(tests[t].topicId === topicId) {
				var opt = document.createElement('option');
			    opt.value = tests[t].id;
			    opt.innerHTML = tests[t].name;
			    testSelect.appendChild(opt);
			}
		}
		
		if(testSelect.options.length == 0) {
			var opt = document.createElement('option');
		    opt.innerHTML = "No tests to list!";
		    testSelect.appendChild(opt);
		}
	}
	
	
	function removeOptions(selectbox)
	{
	    var i;
	    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
	    {
	        selectbox.remove(i);
	    }
	}
