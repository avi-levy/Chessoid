
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := chess
LOCAL_SRC_FILES := \
	liaison.c \
	atak.c \
	book.c \
	cmd.c \
	epd.c \
	eval.c \
	genmove.c \
	hash.c \
	hung.c \
	init.c \
	iterate.c \
	main.c \
	move.c \
	null.c \
	output.c \
	pgn.c \
	players.c \
	quiesce.c \
	random.c \
	repeat.c \
	search.c \
	solve.c \
	sort.c \
	swap.c \
	test.c \
	ttable.c \
	util.c \
	version.c \

include $(BUILD_SHARED_LIBRARY)
