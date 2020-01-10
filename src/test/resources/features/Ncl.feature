@ncl
  Feature: ncl
    @ncl1
      Scenario: Ncl
      Given A Guest
      And I am on Homepage
      And I navigated to "Shore Excursion" page
      When I search for destination "Alaska Cruises"
      Then Shore Excursions page is present
      And Results are filtered by "Alaska Cruises"
      And Filter By Ports are only belong to "Alaska"  or "British Columbia"
