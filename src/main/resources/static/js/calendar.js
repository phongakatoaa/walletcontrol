const monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];

const today = new Date();

class WalletCalendar {
    constructor() {
        this.currentYear = today.getFullYear();
        this.currentMonth = today.getMonth();
        this.beginOfWeek = null;
        $('#yearViewBody td').attr('onclick', 'calendar.onClickMonth(this)');
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
            title = `${this.beginOfWeek.getMonth() + 1}`.padStart(2, '0') + '/'
                + `${this.beginOfWeek.getDate()}`.padStart(2, '0') + '/'
                + this.beginOfWeek.getFullYear() + ' - '
                + `${endOfWeek.getMonth() + 1}`.padStart(2, '0') + '/'
                + `${endOfWeek.getDate()}`.padStart(2, '0') + '/'
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

    showMonthView(month, year) {
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
            if (month !== undefined && year !== undefined) {
                this.currentMonth = month;
                this.currentYear = year;
            } else if (today.getFullYear() === this.currentYear) {
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
        var startDate, endDate;
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
                    if(!startDate) {
                        startDate = new Date(newDate);
                    }
                } else {
                    if (date.getMonth() > this.currentMonth) {
                        cell.addClass('next-month');
                    }
                    if (date.getDate() === today.getDate()
                        && date.getMonth() === today.getMonth()
                        && date.getFullYear() === today.getFullYear()) {
                        cell.css('background-color', '#838383');
                    }
                    const dataDate = `${date.getMonth() + 1}`.padStart(2, '0') + '/'
                        + `${date.getDate()}`.padStart(2, '0') + '/'
                        + date.getFullYear();
                    cell.attr('data-date', moment(dataDate).format("YYYY-MM-DD"));
                    cell.find('.date-display').text(`${date.getMonth() + 1}`.padStart(2, '0') + '/'
                        + `${date.getDate()}`.padStart(2, '0'));
                    cell.attr('onclick', 'calendar.onClickAddPayment(this)');
                    cell.find('.btn-show-all').click(function (e) {
                        e.stopPropagation();
                        calendar.showAllPayments(dataDate);
                    });
                    // this.demoDetail(cell);
                    date.setDate(date.getDate() + 1);
                }
                row.append(cell);
                day++;
                if(!startDate) {
                    startDate = new Date(date);
                }
            }
            day = 1;
            week++;
        }
        endDate = new Date(date);
        this.insertDailyData(startDate, endDate)
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
            const dataDate = `${tmp.getMonth() + 1}`.padStart(2, '0') + '/'
                + `${tmp.getDate()}`.padStart(2, '0') + '/'
                + tmp.getFullYear();
            $(headers[i]).text(`${tmp.getMonth() + 1}`.padStart(2, '0') + '/'
                + `${tmp.getDate()}`.padStart(2, '0'));
            cell.attr('data-date', moment(dataDate).format('YYYY-MM-DD'));
            cell.attr('onclick', 'calendar.onClickAddPayment(this)');
            cell.find('.btn-show-all').click(function (e) {
                e.stopPropagation();
                calendar.showAllPayments(dataDate);
            });

            cell.find('.no-payment').css('display', 'block');
            // cell.find('.list').hide();

            if (tmp.getDate() === today.getDate()
                && tmp.getMonth() === today.getMonth()
                && tmp.getFullYear() === today.getFullYear()) {
                cell.css('background-color', '#838383');
            }
            row.append(cell);
            tmp.setDate(tmp.getDate() + 1);
        }
        const startDate = this.beginOfWeek;
        const endDate = tmp;
        this.insertDailyData(startDate, endDate)
    }

    populateYearView() {
        this.updateTitle();
        $('#yearViewBody td').attr('data-year', this.currentYear).find('.month-total').hide();
        $('#yearViewBody td').find('.no-payment').show();
        this.insertMonthlyData(this.currentYear)
    }

    onClickAddPayment(el) {
        var $el = $(el);
        $('#paymentForm').form('set values', {
            date: $el.attr('data-date')
        });
        $('#paymentModal').modal('show');
    }

    onClickMonth(el) {
        var $el = $(el);
        var month = $el.attr('data-month');
        var year = $el.attr('data-year');
        calendar.showMonthView(month, year);
    }

    showAllPayments(date) {
        alert(`Showing ${date} details...`);
    }

    demoDetail(el) {
        const products = [
            {
                name: 'Lavie',
                cost: '10000',
                colorCode: '#0288D1',
            },
            {
                name: 'Spotify',
                cost: '50000',
                colorCode: '#388E3C'
            },
            {
                name: 'Iphone SE',
                cost: '11000000',
                colorCode: '#D32F2F'
            }];

        var btnShowAll = el.find('.btn-show-all');
        var currencyFormatter = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
        products.forEach(function (p) {
            var $payment = $($('script#template-day-cell-payments').text());
            var currency = currencyFormatter.format(p.cost);
            $payment.find('.payment-name').text(p.name);
            $payment.find('.payment-cost').text(currency);
            $payment.find('.circle').css('color', p.colorCode);
            btnShowAll.before($payment);
        });
        btnShowAll.show();
    }

    insertDailyData(startDate, endDate) {
        var startDateStr = moment(startDate).format("YYYY-MM-DD");
        var endDateStr = moment(endDate).format("YYYY-MM-DD");
        $.ajax({
            url: `/payment/${startDateStr}/${endDateStr}`,
            method: 'get',
            success: function (payments) {
                var currencyFormatter = new Intl.NumberFormat('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                });
                payments.forEach(function (p) {
                    var $payment = $($('script#template-day-cell-payments').text());
                    const cell = $(`td[data-date="${p.date}"]`);
                    var btnShowAll = cell.find('.btn-show-all');
                    var currency = currencyFormatter.format(p.cost);
                    $payment.find('.payment-name').text(p.product);
                    $payment.find('.payment-cost').text(currency);
                    $payment.find('.circle').css('color', p.category.color);
                    btnShowAll.before($payment);
                    cell.find('.no-payment').hide();
                })
            }
        })
    }

    insertMonthlyData(year) {
        $.ajax({
            url: `/payment/year/${year}`,
            method: 'get',
            success: function (totals) {
                var currencyFormatter = new Intl.NumberFormat('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                });
                for (let month = 0; month < 11; month++) {
                    const cell = $(`td[data-month="${month}"]`);
                    if(totals[month] !== 0) {
                        cell.find('.month-total').show();
                        cell.find('.month-total').text(currencyFormatter.format(totals[month]));
                        cell.find('.no-payment').hide();
                    }
                }
            }
        })
    }
}

function getMonday(d) {
    var day = d.getDay(),
        diff = d.getDate() - day + (day === 0 ? -6 : 1); // adjust when day is sunday
    return new Date(d.setDate(diff));
}

const calendar = new WalletCalendar();

$(document).ready(function () {
    initMaster();
    $('#paymentModal').modal({
        onShow: function () {
            $('#standard_calendar').calendar({
                type: 'date',
            });
        }
    });
    $('#paymentForm').form({
        onSuccess: function (evt, data) {
            console.log(moment(data.date).format('MM-DD-YYYY'));
            $.ajax({
                url: '/payment',
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify({
                    id: 0,
                    product: data.product,
                    date: moment(data.date).format('YYYY-MM-DD'),
                    cost: Number(data.cost),
                    categoryId: parseInt(data.categoryId),
                    detail: data.detail,
                    userId: parseInt(data.userId)
                }),
                success: function (data) {
                    alert('success');
                },
                error: function () {
                    alert('error');
                }
            });
        },
        fields: {
            product: {
                identifier: 'product',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Please enter payment product'
                    }
                ]
            },
            categoryId: {
                identifier: 'categoryId',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Please select category'
                    }
                ]
            },
            date: {
                identifier: 'date',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Please enter payment date'
                    }
                ]
            },
            cost: {
                identifier: 'cost',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Please enter payment cost'
                    },
                    {
                        type: 'number',
                        prompt: 'Cost must be a number'
                    },
                ]
            }
        }
    });
});