import time

public class Quiz:
    def __init__(self):
        self.questions = [
            {"question": "What is the capital of France?", "options": ["Paris", "London", "Berlin", "Rome"], "correct": 0},
            {"question": "What is the largest planet in our solar system?", "options": ["Earth", "Saturn", "Jupiter", "Uranus"], "correct": 2},
            # Add more questions here
        ]
        self.score = 0
        self.current_question = 0

    def display_question(self):
        question = self.questions[self.current_question]
        print(f"Question {self.current_question + 1}: {question['question']}")
        for i, option in enumerate(question["options"]):
            print(f"{i + 1}. {option}")

    def get_answer(self):
        while True:
            try:
                answer = int(input("Enter the number of your answer: ")) - 1
                if 0 <= answer < len(self.questions[self.current_question]["options"]):
                    return answer
                else:
                    print("Invalid option. Please try again.")
            except ValueError:
                print("Invalid input. Please try again.")

    def check_answer(self, answer):
        question = self.questions[self.current_question]
        if answer == question["correct"]:
            print("Correct!")
            self.score += 1
        else:
            print(f"Sorry, the correct answer is {question['options'][question['correct']]}")

    def start_timer(self, seconds):
        start_time = time.time()
        while time.time() - start_time < seconds:
            print(f"Time remaining: {int(seconds - (time.time() - start_time))} seconds", end="\r")
            time.sleep(1)
        print("Time's up!")

    def play(self):
        for self.current_question in range(len(self.questions)):
            self.display_question()
            self.start_timer(30)  # 30 seconds per question
            answer = self.get_answer()
            self.check_answer(answer)
            print()
        self.display_results()

    def display_results(self):
        print("Quiz results:")
        print(f"Score: {self.score} / {len(self.questions)}")
        for i, question in enumerate(self.questions):
            if i < self.current_question:
                print(f"Question {i + 1}: {question['question']}")
                if question["correct"] == self.questions[i]["options"].index(input("Enter your answer: ")):
                    print("Correct!")
                else:
                    print(f"Sorry, the correct answer is {question['options'][question['correct']]}")
            else:
                break

quiz = Quiz()
quiz.play()