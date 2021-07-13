#handleFile function return structure

fileObj: 
  Name: "name"
  Runlocation: "/sap/abc"
  toBeDeleted: "true/false, only true you need to care about this property"
  # In case this object goes into a different 
  # container. Possibly multiple files need to 
  # be created instead of this on
  toBeCreated:
    # this is an array, full path name
    - filename: "[by default from project folder]/web/bla/bla/a.config"
      content: "file content"
      update_content: 
      container: <container name>
    - filename: ...
      content: ...
      container: <container name>
  # in case files need to be updated
  toBeUpdated: 
    - filename: "[by dufault from project folder]/web/bla/bla/a.config"
      content: "test"
      func: "function handler for this content"
  messages: 
    - file: "name"
      loc: "object of lines info"
      message: "infos"
      type: "error/warning"
 