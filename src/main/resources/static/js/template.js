function holeUnusedTableButtonStyle(tableNum) {
    return holeTableButtonStylePrefix(tableNum)
            + "hover:bg-gray-400 '>"
            + tableNum
            + holeTableButtonStyleSuffix();
}

function holeUsedTableButtonStyle(tableNum) {
    return holeTableButtonStylePrefix(tableNum)
            + "bg-green-100 hover:bg-gray-400'>"
            + tableNum
            + holeTableButtonStyleSuffix();
}

function holeSelectTableButtonStyle(tableNum) {
    return holeTableButtonStylePrefix(tableNum)
            + "bg-gray-400'>"
            + tableNum
            + holeTableButtonStyleSuffix();
}

function holeTableButtonStylePrefix(tableNum){
    return "<button value='" + tableNum + "' class='"
                + "inline-flex items-center justify-center whitespace-nowrap rounded-md "
                + "text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none "
                + "focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 "
                + "disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground "
                + "hover:bg-primary/90 h-10 px-4 py-2 ";
}

function holeTableButtonStyleSuffix(){
    return "</button>"
}

function holeTableButtonClickStyle(clickButton){
    $('#holeTable button').removeClass('bg-gray-400');
    $(clickButton).addClass('bg-gray-400');
}

function holeTableOrderListStyle(order) {
    let orderListStyle = '';

    order.orderItems.forEach((orderItem) => {
        orderListStyle += '<tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted bg-blue-200">'
                        + '<td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium">'
                        + orderItemName(orderItem)
                        + '</td>'
                        + '<td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium">'
                        + formatCurrencyKr(orderItem.totalPrice / orderItem.count)
                        + '</td>'
                        + '<td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium">'
                        + orderItem.count
                        + '</td>'
                        + '<td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium">'
                        + formatCurrencyKr(orderItem.totalPrice)
                        + '</td>'
                        + '</tr>';
    });

    return orderListStyle;
}

function orderItemName(orderItem) {
    let name = orderItem.menu.name + ' // ';
    orderItem.menuOptions.forEach((menuOption) => {
        name += menuOption.name + ', '
    })
    return name;
}

function formatCurrencyKr(amount) {
    return amount.toLocaleString('ko-KR') + '원';
}