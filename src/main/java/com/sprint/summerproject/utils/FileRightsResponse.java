package com.sprint.summerproject.utils;

import java.util.List;

public class FileRightsResponse {
    List<String> viewMembers;
    List<String> editMembers;

    public FileRightsResponse(List<String> viewMembers, List<String> editMembers) {
        this.viewMembers = viewMembers;
        this.editMembers = editMembers;
    }
}
