# Check for Java compiler.
# For now we only handle the GNU compiler.

# Copyright (C) 1999, 2000, 2003 Free Software Foundation, Inc.

# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2, or (at your option)
# any later version.

# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.

# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
# 02110-1301, USA.

AC_DEFUN([AM_PROG_GCJ],[
AC_CHECK_PROGS(GCJ, gcj, false)
test -z "$GCJ" && AC_MSG_ERROR([no acceptable gcj found in \$PATH])
if test "x${GCJFLAGS-unset}" = xunset; then
   GCJFLAGS="-g -O2"
fi
AC_SUBST(GCJFLAGS)
_AM_IF_OPTION([no-dependencies],, [_AM_DEPENDENCIES(GCJ)])
])
