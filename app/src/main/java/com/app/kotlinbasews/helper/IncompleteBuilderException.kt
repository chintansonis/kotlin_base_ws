package com.app.kotlinbasews.helper


class IncompleteBuilderException : IllegalArgumentException {

    /**
     * Constructs an IncompleteBuilderException with no detail message.
     * A detail message is a String that describes this particular
     * exception.
     */
    constructor() : super() {}

    /**
     * Constructs an IncompleteBuilderException with the specified
     * detail message.  A detail message is a String that describes
     * this particular exception.
     *
     * @param msg the detail message.
     */
    constructor(msg: String) : super(msg) {}
}