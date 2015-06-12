
// Track the element that is being dragged.var
elementDragged = null;

function createText(elem){
  var text = elem.innerHTML;
  var header = elem.getElementsByTagName("h4");
  if(header.length > 0){
    text = header[0].innerHTML;
  }
  return text;
}

function addDrag(elem){

  // Event Listener for when the drag interaction starts.
  elem.addEventListener('dragstart', function(e) {
    e.dataTransfer.effectAllowed = 'move';
    e.dataTransfer.setData('text', createText(this));
    elementDragged = this;
  });

  // Event Listener for when the drag interaction finishes.
  elem.addEventListener('dragend', function(e) {
    elementDragged = null;
  });
}

function setDrag(elems){
  for (var i = 0; i < elems.length; i++) {
    addDrag(elems[i]);
  };
}

function addDelete(elem){
  elem.addEventListener('click', function(e){
      if((e.target === this || e.target.tagName == "H4" ) && isDeletable(this)){
        this.parentNode.removeChild(this);
      }
    });

  elem.addEventListener('mouseover', function(e){
      if(isDeletable(this)){
        this.className = addClass(this.className, "deletable");
      }
    });

  elem.addEventListener('mouseout', function(e){
      this.className = removeClass(this.className, "deletable");
    });
}

function setDelete(elems){
  for (var i = 0; i < elems.length; i++) {
    addDelete(elems[i]);
  };
}

function addDrop(elem){
  // Event Listener for when the dragged element is over the drop zone.
  elem.addEventListener('dragover', function(e) {
      if (e.preventDefault) {
        e.preventDefault();
      }

      e.dataTransfer.dropEffect = 'move';

      return false;
    });

    // Event Listener for when the dragged element enters the drop zone.
  elem.addEventListener('dragenter', function(e) {
      this.className = addClass(this.className, "droppable");
    });

    // Event Listener for when the dragged element leaves the drop zone.
  elem.addEventListener('dragleave', function(e) {
      this.className = removeClass(this.className, "droppable");
    });

    // Event Listener for when the dragged element dropped in the drop zone.
  elem.addEventListener('drop', function(e) {
      if (e.preventDefault) e.preventDefault(); 
      if (e.stopPropagation) e.stopPropagation();
      //if (isIn(e.target,this.childNodes)) return false;

      this.className = removeClass(this.className, "droppable");

      var node = document.createElement("li");
      var textnode = document.createTextNode(e.dataTransfer.getData('text'));
      node.appendChild(textnode);
      node.className = addClass(this.className, "minigradable");
      var child = this.getElementsByTagName("ul")[0].appendChild(node);
      addDelete(child);
      return false;
    });
}

function setDrop(elems){
  for (var i = 0; i < elems.length; i++) {
    addDrop(elems[i]);
  }
}

function addClass(currentClass, add){
  return currentClass + " " + add;
}

function removeClass(currentClass, remove){
  var resultClass = "";
  var currentClasses = currentClass.split(" ");
  for(var i = 0; i < currentClasses.length; i++){
    if(currentClasses[i] != remove){
      resultClass = addClass(resultClass, currentClasses[i]);
    }
  }
  return resultClass;
}

function isDeletable(elem){
  return !isIn("requirement", elem.className.split(" ")) || elem.getElementsByTagName("li").length == 0;
}

function isIn(elem, array){
  for(var i = 0; i < array.length; i++){
    if(elem == array[i]){
      return true;
    }
  }
  return false;
}