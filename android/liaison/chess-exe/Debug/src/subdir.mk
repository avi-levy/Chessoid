################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/atak.c \
../src/book.c \
../src/cmd.c \
../src/epd.c \
../src/eval.c \
../src/genmove.c \
../src/hash.c \
../src/hung.c \
../src/init.c \
../src/iterate.c \
../src/main.c \
../src/move.c \
../src/null.c \
../src/output.c \
../src/pgn.c \
../src/players.c \
../src/quiesce.c \
../src/random.c \
../src/repeat.c \
../src/search.c \
../src/solve.c \
../src/sort.c \
../src/swap.c \
../src/test.c \
../src/ttable.c \
../src/util.c \
../src/version.c 

OBJS += \
./src/atak.o \
./src/book.o \
./src/cmd.o \
./src/epd.o \
./src/eval.o \
./src/genmove.o \
./src/hash.o \
./src/hung.o \
./src/init.o \
./src/iterate.o \
./src/main.o \
./src/move.o \
./src/null.o \
./src/output.o \
./src/pgn.o \
./src/players.o \
./src/quiesce.o \
./src/random.o \
./src/repeat.o \
./src/search.o \
./src/solve.o \
./src/sort.o \
./src/swap.o \
./src/test.o \
./src/ttable.o \
./src/util.o \
./src/version.o 

C_DEPS += \
./src/atak.d \
./src/book.d \
./src/cmd.d \
./src/epd.d \
./src/eval.d \
./src/genmove.d \
./src/hash.d \
./src/hung.d \
./src/init.d \
./src/iterate.d \
./src/main.d \
./src/move.d \
./src/null.d \
./src/output.d \
./src/pgn.d \
./src/players.d \
./src/quiesce.d \
./src/random.d \
./src/repeat.d \
./src/search.d \
./src/solve.d \
./src/sort.d \
./src/swap.d \
./src/test.d \
./src/ttable.d \
./src/util.d \
./src/version.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


