package model.data;

import model.util.Utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MockDatabase implements Database {
    private final Set<GradedBook> bookSet = Set.of(
            new GradedBook("JoJo no Kimyou na Bouken Part 7: Steel Ball Run", 928),
            new GradedBook("Vagabond", 920),
            new GradedBook("Berserk", 946),
            new GradedBook("Slam Dunk", 907),
            new GradedBook("One Piece", 921),
            new GradedBook("Oyasumi Punpun", 903),
            new GradedBook("Monster", 914),
            new GradedBook("Grand Blue", 902),
            new GradedBook("Fullmetal Alchemist", 904),
            new GradedBook("Vinland Saga", 900)
    );

    private final Map<String, Set<Reaction>> reactions = new HashMap<>();

    @Override
    public CompletableFuture<Set<GradedBook>> getAllBooks() {
        return CompletableFuture.completedFuture(bookSet);
    }

    @Override
    public CompletableFuture<GradedBook> getByTitle(String title) {
        for (GradedBook m : bookSet) {
            if (m.getTitle().equals(title)) {
                return CompletableFuture.completedFuture(m);
            }
        }

        return CompletableFuture.failedFuture(new IllegalArgumentException("GradedBook not found: " + title));
    }

    @Override
    public CompletableFuture<Void> updateGrade(String title, int update) {
        for (GradedBook m : bookSet) {
            if (m.getTitle().equals(title)) {
                m.setGrade(m.getGrade() + update);
                return CompletableFuture.completedFuture(null);
            }
        }

        return CompletableFuture.failedFuture(new IllegalArgumentException("GradedBook not found: " + title));
    }

    @Override
    public CompletableFuture<Void> updateReaction(String username, String title, boolean liked) {
        reactions.putIfAbsent(username, new HashSet<>());
        reactions.get(username).add(new Reaction(title, liked));
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Set<Reaction>> getReactionsByUser(String username) {
        if (reactions.containsKey(username)) {
            return CompletableFuture.completedFuture(new HashSet<>(reactions.get(username)));
        }

        return CompletableFuture.failedFuture(new IllegalArgumentException("User not found: " + username));
    }
}
