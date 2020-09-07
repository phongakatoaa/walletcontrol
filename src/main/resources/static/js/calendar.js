const monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];

const today = new Date();

class WalletCalendar {
    constructor() {
        this.currentYear = today.getFullYear();
        this.currentMonth = today.getMonth();
        this.beginOfWeek = null;
        this.mode = 'month';
        this.populateMonthView();
    }

    updateTitle() {
        var title;
        if (this.mode === 'month') {
            title = monthNames[this.currentMonth] + ' ' + this.currentYear;
        } else if (this.mode === 'week') {
            var endOfWeek = new Date(this.beginOfWeek);
            endOfWeek.setDate(endOfWeek.getDate() + 6);
            title = `${this.beginOfWeek.getDate()}`.padStart(2, '0') + '/'
                + `${this.beginOfWeek.getMonth() + 1}`.padStart(2, '0') + '/'
                + this.beginOfWeek.getFullYear() + ' - '
                + `${endOfWeek.getDate()}`.padStart(2, '0') + '/'
                + `${endOfWeek.getMonth() + 1}`.padStart(2, '0') + '/'
                + endOfWeek.getFullYear();
        } else if (this.mode === 'year') {
            title = this.currentYear;
        }
        $('#title').text(title);
    }

    showWeekView() {
        if (this.mode === 'week') return;
        $('#controls .item').removeClass('active');
        $('#btnWeekView').addClass('active');
        $('#monthViewHead').hide();
        $('#monthViewBody').hide();
        $('#yearViewBody').hide();
        $('#weekViewHead').show();
        $('#weekViewBody').show();
        if (this.mode === 'month') {
            this.beginOfWeek = getMonday(new Date(this.currentYear, this.currentMonth, 1));
        } else if (this.mode === 'year') {
            this.beginOfWeek = getMonday(new Date(this.currentYear, 0, 1));
        }
        this.mode = 'week';
        this.populateWeekView();
    }

    showMonthView() {
        if (this.mode === 'month') return;
        $('#controls .item').removeClass('active');
        $('#btnMonthView').addClass('active');
        $('#monthViewHead').show();
        $('#monthViewBody').show();
        $('#yearViewBody').hide();
        $('#weekViewHead').hide();
        $('#weekViewBody').hide();
        if (this.mode === 'week') {
            this.currentMonth = this.beginOfWeek.getMonth();
            this.currentYear = this.beginOfWeek.getFullYear();
        } else if (this.mode === 'year') {
            if (today.getFullYear() === this.currentYear) {
                this.currentMonth = today.getMonth();
            } else {
                this.currentMonth = 0;
            }
        }
        this.mode = 'month';
        this.populateMonthView();
    }

    showYearView() {
        if (this.mode === 'year') return;
        $('#controls .item').removeClass('active');
        $('#btnYearView').addClass('active');
        $('#monthViewHead').hide();
        $('#monthViewBody').hide();
        $('#yearViewBody').show();
        $('#weekViewHead').hide();
        $('#weekViewBody').hide();
        if (this.mode === 'week') {
            this.currentYear = this.beginOfWeek.getFullYear();
        }
        this.mode = 'year';
        this.populateYearView();
    }

    next() {
        if (this.mode === 'month') {
            if (this.currentMonth === 11) {
                this.currentMonth = 0;
                this.currentYear++;
            } else {
                this.currentMonth++;
            }
            this.populateMonthView();
        } else if (this.mode === 'week') {
            this.beginOfWeek.setDate(this.beginOfWeek.getDate() + 7);
            this.populateWeekView();
        } else if (this.mode === 'year') {
            this.currentYear++;
            this.populateYearView();
        }
    }

    prev() {
        if (this.mode === 'month') {
            if (this.currentMonth === 0) {
                this.currentMonth = 11;
                this.currentYear--;
            } else {
                this.currentMonth--;
            }
            this.populateMonthView();
        } else if (this.mode === 'week') {
            this.beginOfWeek.setDate(this.beginOfWeek.getDate() - 7);
            this.populateWeekView();
        } else if (this.mode === 'year') {
            this.currentYear--;
            this.populateYearView();
        }
    }

    populateMonthView() {
        this.updateTitle();
        var monthViewBody = $('#monthViewBody').empty();
        var date = new Date(this.currentYear, this.currentMonth, 1);
        var week = 1;
        var day = 1;
        while (week <= 5) {
            var row = $('<tr></tr>');
            monthViewBody.append(row);
            while (day <= 7) {
                var cell = $($('script#template-month-view-day-cell').text());
                if (day < date.getDay()) {
                    cell.addClass('previous-month');
                    var newDate = new Date(this.currentYear, this.currentMonth, 1);
                    newDate.setDate(newDate.getDate() - (date.getDay() - day));
                    cell.find('.date-display').text(`${newDate.getDate()}`.padStart(2, '0') + '/'
                        + `${newDate.getMonth() + 1}`.padStart(2, '0'));
                } else {
                    if (date.getMonth() > this.currentMonth) {
                        cell.addClass('next-month');
                    } else {

                    }
                    if (date.getDate() === today.getDate()
                        && date.getMonth() === today.getMonth()
                        && date.getFullYear() === today.getFullYear()) {
                        cell.css('background-color', '#A0A0A0');
                    }
                    cell.find('.date-display').text(`${date.getDate()}`.padStart(2, '0') + '/'
                        + `${date.getMonth() + 1}`.padStart(2, '0'));
                    date.setDate(date.getDate() + 1);
                }
                row.append(cell);
                day++;
            }
            day = 1;
            week++;
        }
    }

    populateWeekView() {
        this.updateTitle();
        var weekViewBody = $('#weekViewBody').empty();
        var row = $('<tr></tr>');
        weekViewBody.append(row);
        var tmp = new Date(this.beginOfWeek);
        var headers = $('#weekViewHead .week-date');
        for (var i = 0; i <= 6; i++) {
            var cell = $($('script#template-week-view-day-cell').text());
            $(headers[i]).text(`${tmp.getDate()}`.padStart(2, '0') + '/'
                + `${tmp.getMonth() + 1}`.padStart(2, '0'));
            if (tmp.getDate() === today.getDate()
                && tmp.getMonth() === today.getMonth()
                && tmp.getFullYear() === today.getFullYear()) {
                cell.css('background-color', '#A0A0A0');
            }
            row.append(cell);
            tmp.setDate(tmp.getDate() + 1);
        }
    }

    populateYearView() {
        this.updateTitle();
    }
}

function getMonday(d) {
    var day = d.getDay(),
        diff = d.getDate() - day + (day === 0 ? -6 : 1); // adjust when day is sunday
    return new Date(d.setDate(diff));
}

const calendar = new WalletCalendar();