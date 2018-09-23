/**
 *
 * @author Madhuri Vutukry
 */
/**
 * Ge the topics based on technologyId
 */

function getTopics() {
    removeTopics();
    var xhttp;
    var technology = document.getElementsByName("technologies")[0];
    //techid =selected option id
    var techId = technology.options[technology.selectedIndex].id;
    console.log(techId);
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            //converting text to json
            var listOfTopics = JSON.parse(this.responseText);
            var select = document.getElementsByName("topics")[0];
            OptioPleaseSelect()
            for (var loop = 0; loop < listOfTopics.length; loop++) {
                var option = document.createElement("option");
                //display value
                option.text = listOfTopics[loop].name;
                //topic id
                option.value = listOfTopics[loop].id;
                select.add(option);
            }
            console.log(option)
            console.log("Done")
        }
    };

    //request param
    xhttp.open("GET", "/trainer/getTopics.htm?techId=" + techId, true);
    xhttp.send();
}

/**
 * removeTopics removes the list of topics
 * (This is used when we select technology type from the dropdownlist, the previos list of topics will be deleted.
 * ANd displayed the current technology topics.)
 *
 */

function removeTopics() {
    var x = document.getElementsByName("topics")[0];
    var y = document.getElementsByName("topics")[0].length;
    for (loop = 0; loop < y; loop++) {
        x.remove(x.loop);
    }
}

/**
 *
 * In the topic list first option always please select topic
 */

function OptioPleaseSelect() {
    var x = document.getElementsByName("topics")[0];
    var option = document.createElement("option");
    option.text = 'Please Select Topic';
    x.add(option);
}
