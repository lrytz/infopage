$(window).load(function() {
  $('.hcaption').hcaptions();
});

$("#bilderGallery").justifiedGallery({
  rowHeight : 260,
  lastRow : 'nojustify',
  rel : 'gallery1',
  randomize: true,
  margins : 7
}).on('jg.complete', function () {
    $(this).find('a').colorbox({
        maxWidth : '80%',
        maxHeight : '80%',
        opacity : 0.8,
        transition : 'elastic',
        current : ''
    });
});
