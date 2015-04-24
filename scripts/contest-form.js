var submissionUrl     = "http://127.0.0.1:8080/contestSubmission";
var contestForm       = $("#contestForm");
var contestModal      = $("#contest-modal")
var contestModalTitle = $("#contest-modal-title")
var contestModalBody  = $("#contest-modal-body")

function showInfo(title, message, cls) {
  if (typeof(cls)==='undefined') cls = "alert-success";
  contestModalTitle.html(title);
  contestModalBody.removeClass();
  contestModalBody.addClass("alert " + cls);
  contestModalBody.html(message);
  contestModal.modal('show');
}

function showError() {
  showInfo("Fehler", "Ein Fehler ist aufgetreten, bitte suchen Sie Hilfe.", "alert-danger")
}

function radioAnswer(arg) {
  if (arg == null) return "";
  else return arg;
}

function checkboxAnswer(arg) {
  return arg.filter(function(v) { return v != null }).join(",");
}

contestForm.validate({
  rules: {
    smgGruendung: "required",
    segelflugzeugSekundaer: "required",
    drehAchse: "required",
    jugendProgramm: "required",
    alterSegelflug: "required",
    richtung: "required"
  }
});

var contestSubmitSuccess = function(data, textStatus, jqXHR) {
  if (data == "KTHXBYE") {
    showInfo("Vielen Dank", "<strong>Vielen Dank!</strong> Ihre Teilname wurde registriert.");
    contestForm.trigger("reset");
  } else {
    showError()
    console.log(data);
    console.log(textStatus);
  }
}

var contestSubmitError = function(jqXHR, textStatus, errorThrown) {
  showError()
  console.log(textStatus);
  console.log(errorThrown);
}

// https://github.com/macek/jquery-serialize-object
$("#contestFormSubmit").click(function(event) {
  event.preventDefault();
  if (contestForm.valid()) {
    var data = contestForm.serializeObject();
    var toSend = {
      contact: {
        first: data.inputFirst,
        last:  data.inputLast,
        email: data.inputEmail
      },
      // answers: [data.answer1, radioAnswer(data.answer2), checkboxAnswer([data.answer3_1, data.answer3_2, data.answer3_3])]
      answers: [
        radioAnswer(data.smgGruendung),
        radioAnswer(data.segelflugzeugSekundaer),
        radioAnswer(data.drehAchse),
        radioAnswer(data.jugendProgramm),
        radioAnswer(data.alterSegelflug),
        radioAnswer(data.richtung)
      ]
    };

    $.ajax({
      url: submissionUrl,
      type: "POST",
      data: JSON.stringify(toSend),
      contentType: "application/json; charset=utf-8",
      dataType: "text",
      success: contestSubmitSuccess,
      error: contestSubmitError
    })
  }
})
