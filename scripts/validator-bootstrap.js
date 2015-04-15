// http://stackoverflow.com/questions/18754020/bootstrap-3-with-jquery-validation-plugin

$.validator.setDefaults({
    highlight: function(element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function(element) {
        $(element).closest('.form-group').removeClass('has-error');
    },
    errorElement: 'span',
    errorClass: 'help-block',
    errorPlacement: function(error, element) {
        if(element.parent('.input-group').length) {
            error.insertAfter(element.parent());
        } else {
            error.insertAfter(element);
        }
    }
});

$.extend($.validator.messages, {
    required: "Dieses Feld ist erforderlich.",
    remote: "Bitte korrigieren Sie dieses Feld.",
    email: "Bitte geben Sie eine gültige E-Mail Adresse ein.",
    url: "Bitte geben Sie eine gültige URL ein.",
    date: "Bitte geben Sie ein gültiges Datum ein.",
    dateISO: "Bitte geben Sie ein gültiges Datum ein (ISO).",
    number: "Bitte geben Sie eine gültige Zahl ein.",
    digits: "Bitte geben Sie nur Zahlen ein.",
    creditcard: "Bitte geben Sie eine gültige Kreditkarten-Nummer ein.",
    equalTo: "Bitte geben Sie den selben wert noch einmal ein.",
    accept: "Bitte geben Sie einen Wert mit einer gültigen Erweiterung ein.",
    maxlength: jQuery.validator.format("Bitte geben Sie nicht mehr als {0} Zeichen ein."),
    minlength: jQuery.validator.format("Bitte geben Sie mindestens {0} Zeichen ein."),
    rangelength: jQuery.validator.format("Bitte geben Sie zwischen {0} und {1} Zeichen ein."),
    range: jQuery.validator.format("Bitte geben Sie einen Wert zwischen {0} und {1} ein."),
    max: jQuery.validator.format("Bitte geben Sie einen Wert kleiner oder gleich {0} ein."),
    min: jQuery.validator.format("Bitte geben Sie einen Wert grösser oder gleich {0} ein.")
});
