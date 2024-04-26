package com.nbcteam5.nbccontact.data

import android.content.ContentResolver
import android.database.CharArrayBuffer
import android.database.ContentObserver
import android.database.Cursor
import android.database.DataSetObserver
import android.net.Uri
import android.os.Bundle

data class query(
    val url: Uri,
    val projection: List<String>,
    val selection: String,
    val selectionArgs: List<String>,
    val sortOrder: String
) : Cursor {
    override fun close() {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getPosition(): Int {
        TODO("Not yet implemented")
    }

    override fun move(p0: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun moveToPosition(p0: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun moveToFirst(): Boolean {
        TODO("Not yet implemented")
    }

    override fun moveToLast(): Boolean {
        TODO("Not yet implemented")
    }

    override fun moveToNext(): Boolean {
        TODO("Not yet implemented")
    }

    override fun moveToPrevious(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isFirst(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLast(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBeforeFirst(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAfterLast(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getColumnIndex(p0: String?): Int {
        TODO("Not yet implemented")
    }

    override fun getColumnIndexOrThrow(p0: String?): Int {
        TODO("Not yet implemented")
    }

    override fun getColumnName(p0: Int): String {
        TODO("Not yet implemented")
    }

    override fun getColumnNames(): Array<String> {
        TODO("Not yet implemented")
    }

    override fun getColumnCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getBlob(p0: Int): ByteArray {
        TODO("Not yet implemented")
    }

    override fun getString(p0: Int): String {
        TODO("Not yet implemented")
    }

    override fun copyStringToBuffer(p0: Int, p1: CharArrayBuffer?) {
        TODO("Not yet implemented")
    }

    override fun getShort(p0: Int): Short {
        TODO("Not yet implemented")
    }

    override fun getInt(p0: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(p0: Int): Float {
        TODO("Not yet implemented")
    }

    override fun getDouble(p0: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getType(p0: Int): Int {
        TODO("Not yet implemented")
    }

    override fun isNull(p0: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun deactivate() {
        TODO("Not yet implemented")
    }

    override fun requery(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isClosed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun registerContentObserver(p0: ContentObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterContentObserver(p0: ContentObserver?) {
        TODO("Not yet implemented")
    }

    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun setNotificationUri(p0: ContentResolver?, p1: Uri?) {
        TODO("Not yet implemented")
    }

    override fun getNotificationUri(): Uri {
        TODO("Not yet implemented")
    }

    override fun getWantsAllOnMoveCalls(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setExtras(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun getExtras(): Bundle {
        TODO("Not yet implemented")
    }

    override fun respond(p0: Bundle?): Bundle {
        TODO("Not yet implemented")
    }
}
