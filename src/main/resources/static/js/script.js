/**
 * Created by mtoader on 7/17/2017.
 */

$(document).on("click",".finished-game", function () {
    $(this).children('form').first().submit();
});

function payBet(isPaying, betId) {
    $.ajax(this.href, {
        success: function(data) {
            $('#main').html($(data).find('#main *'));
            $('#notification-bar').text('The page has been successfully loaded');
        },
        error: function() {
            $('#notification-bar').text('An error occurred');
        }
    });
}