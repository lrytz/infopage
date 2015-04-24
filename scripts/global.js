$(window).load(function() {
  $('.hcaption').hcaptions();
});

colorboxSettings = {
  maxWidth : '80%',
  maxHeight : '80%',
  opacity : 0.8,
  transition : 'elastic',
  rel: 'gallery',
  current : ''
}

$("#bilderGallery").justifiedGallery({
  rowHeight : 260,
  lastRow : 'nojustify',
  randomize: true,
  margins : 7
}).on('jg.complete', function () {
  $(this).find('a').colorbox(colorboxSettings);
});

$('a.cbox').colorbox(colorboxSettings);
