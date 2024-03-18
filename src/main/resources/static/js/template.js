function holeUnusedTableButtonStyle(tableNum) {
    return holeTableButtonStylePrefix()
            + "hover:bg-gray-400'>"
            + tableNum
            + holeTableButtonStyleSuffix();
}

function holeUseTableButtonStyle(tableNum) {
    return holeTableButtonStylePrefix()
            + "bg-green-100 hover:bg-gray-400'>"
            + tableNum
            + holeTableButtonStyleSuffix();
}

function holeSelectTableButtonStyle(tableNum) {
    return holeTableButtonStylePrefix()
            + "bg-gray-400'>"
            + tableNum
            + holeTableButtonStyleSuffix();
}

function holeTableButtonStylePrefix(){
    return "<button class='"
                + "inline-flex items-center justify-center whitespace-nowrap rounded-md "
                + "text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none "
                + "focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 "
                + "disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground "
                + "hover:bg-primary/90 h-10 px-4 py-2 ";
}

function holeTableButtonStyleSuffix(){
    return "</button>"
}