$(function() {
     
      // scroll handler
      var scrollToAnchor = function( id ) {
     
        // grab the element to scroll to based on the name
        var elem = $("a[id='"+ id +"']");
     
        // if that didn't work, look for an element with our ID
        if ( typeof( elem.offset() ) === "undefined" ) {
          elem = $("#"+id);
        }
     
        // if the destination element exists
        if ( typeof( elem.offset() ) !== "undefined" ) {
     
          // do the scroll
          $('html, body').animate({
                  scrollTop: elem.offset().top
          }, 1000 );
     
        }
      };
     
      // bind to click event
      $("a.scrollnav").click(function( event ) {
     
        // only do this if it's an anchor link
        if ( $(this).attr("href").match("#") ) {
     
          // cancel default event propagation
          event.preventDefault();
     
          // scroll to the location
          var href = $(this).attr('href').replace('#', '');
          scrollToAnchor( href );

        }
      });
    });