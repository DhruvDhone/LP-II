def main():
    print("Employee Performance Evaluation Expert System")

    # Basic Info
    name = input("Enter employee name: ").strip()
    age = int(input("Enter age: ").strip())
    experience = int(input("Years of experience: ").strip())

    print("\n--- Work Performance ---")
    tasks_completed = int(input("Number of tasks completed this month: ").strip())
    deadlines_met = int(input("Number of deadlines met in percentage %: ").strip())
    quality = int(input("Rate quality of work (1-10): ").strip())

    print("\n--- Behavioral Factors ---")
    teamwork = input("Good team player? (yes/no): ").strip().lower()
    communication = int(input("Communication skill (1-10): ").strip())
    attendance = int(input("Attendance percentage: ").strip())

    print("\n--- Skills Assessment ---")
    technical_skill = int(input("Technical skill (1-10): ").strip())
    learning_ability = int(input("Learning ability (1-10): ").strip())
    problem_solving = int(input("Problem solving skill (1-10): ").strip())

    print("\n--- Feedback ---")
    manager_feedback = int(input("Manager rating (1-10): ").strip())
    self_rating = int(input("Self rating (1-10): ").strip())

    print("\n--- Evaluation Report ---")

    # Flags
    high_performer = False
    needs_improvement = False
    critical_issue = False

    score = 0

    # Work Evaluation
    if tasks_completed >= 20 and deadlines_met >= 90:
        print("Work Output: Excellent")
        score += 3
    elif tasks_completed >= 10:
        print("Work Output: Good")
        score += 2
    else:
        print("Work Output: Low")
        score += 1
        needs_improvement = True

    if quality >= 8:
        print("Quality: High")
        score += 2
    elif quality >= 5:
        print("Quality: Average")
        score += 1
    else:
        print("Quality: Poor")
        score -= 1
        critical_issue = True

    # Behavior Evaluation
    if teamwork == "yes":
        print("Teamwork: Good")
        score += 2
    else:
        print("Teamwork: Needs improvement")
        needs_improvement = True

    if communication >= 7:
        print("Communication: Strong")
        score += 2
    else:
        print("Communication: Weak")
        needs_improvement = True

    if attendance >= 90:
        print("Attendance: Excellent")
        score += 2
    elif attendance >= 75:
        print("Attendance: Acceptable")
        score += 1
    else:
        print("Attendance: Poor")
        critical_issue = True

    # Skills Evaluation
    if technical_skill >= 8:
        print("Technical Skill: Strong")
        score += 2
    else:
        print("Technical Skill: Moderate/Low")
        needs_improvement = True

    if learning_ability >= 7:
        print("Learning Ability: Good")
        score += 2
    else:
        print("Learning Ability: Slow")
        needs_improvement = True

    if problem_solving >= 7:
        print("Problem Solving: Good")
        score += 2
    else:
        print("Problem Solving: Weak")
        needs_improvement = True

    # Feedback Evaluation
    if manager_feedback >= 8:
        print("Manager Feedback: Excellent")
        score += 3
    elif manager_feedback >= 5:
        print("Manager Feedback: Average")
        score += 1
    else:
        print("Manager Feedback: Poor")
        critical_issue = True

    if self_rating > manager_feedback:
        print("Note: Self-rating higher than manager rating.")
    elif self_rating < manager_feedback:
        print("Note: Employee underestimates performance.")

    # Final Decision
    print("\n--- Final Result ---")

    if score >= 15 and not critical_issue:
        print("Performance: EXCELLENT")
        high_performer = True
    elif score >= 10:
        print("Performance: GOOD")
    elif score >= 5:
        print("Performance: AVERAGE")
        needs_improvement = True
    else:
        print("Performance: POOR")
        critical_issue = True

    # Suggestions
    print("\n--- Suggestions ---")

    if high_performer:
        print("Promotion/bonus recommended.")
    if needs_improvement:
        print("Training and mentoring suggested.")
    if critical_issue:
        print("Immediate performance improvement plan required.")

    if not (high_performer or needs_improvement or critical_issue):
        print("Employee is stable and consistent.")

if __name__ == "__main__":
    main()