package com.epam.rd.autocode.set;

import java.util.*;

public class Project {

    private final List<Role> roles;

    public Project(Role... roles) {
        this.roles = new ArrayList<>();
        this.roles.addAll(Arrays.asList(roles));
    }

    public List<Role> getRoles() {
        return roles;
    }

    public int getConformity(Set<Member> team) {
        List<Entry> projectEntries = new ArrayList<>();
        for (Role role : getRoles()) {
            for (Skill skill : role.getSkills()) {
                projectEntries.add(new Entry(role.getLevel(), skill));
            }
        }
        int originalSize = projectEntries.size();

        List<Entry> teamEntries = new ArrayList<>();
        for (Member member : team) {
            for (Skill skill : member.getSkills()) {
                teamEntries.add(new Entry(member.getLevel(), skill));
            }
        }

        projectEntries.removeAll(teamEntries);

        int difference = originalSize - projectEntries.size();

        return (difference * 100) / originalSize;

    }

    private static class Entry {
        private final Level level;
        private final Skill skill;

        public Entry(Level level, Skill skill) {
            this.level = level;
            this.skill = skill;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return level == entry.level && skill == entry.skill;
        }

        @Override
        public int hashCode() {
            return Objects.hash(level, skill);
        }
    }

}
